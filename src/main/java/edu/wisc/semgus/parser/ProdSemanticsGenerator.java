package edu.wisc.semgus.parser;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import edu.wisc.semgus.utilities.EqType;

import io.github.cvc5.Solver;
import io.github.cvc5.Term;
import io.github.cvc5.Sort;
import io.github.cvc5.Kind;
import io.github.cvc5.CVC5ApiException;

public class ProdSemanticsGenerator {
    // e.g. {E -> {+$ -> [E, E]}} to say production +$ from E takes two E args
    private Map<String, Map<String, String[]>> productionTypes;
    // e.g. {E -> INT, B -> BOOL}
    private Map<String, Sort> universeNTSorts;
    private Solver slv;
    // Sorts used by slv
    private Sort integer;
    private Sort bool;
    // Map from var/const names to CVC5 Terms currently in scope when recursively parsing semantics
    private Map<String, Term> state;
    // Functions applying each nonterminal's semantics - e.g. B.sem(bt, x, y, z) -> r
    private Map<String, Term> semFunctions = new HashMap<String, Term>();

    public ProdSemanticsGenerator(Map<String, Map<String, String[]>> productionTypes, 
                                  Map<String, EqType> universeNTTypes, 
                                  Map<String, EqType[]> semFunctionTypes) 
    throws CVC5ApiException {
        Solver slv = new Solver();
                
        slv.setOption("produce-models", "true"); // Produce Models
        slv.setOption("dag-thresh", "0"); // Disable dagifying the output
        slv.setOption("output-language", "smt2"); // use smt-lib v2 as output language
        // quantifier-free uninterpreted functions + nonlinear arithmetic (custom semantics can use nonlinear arithmetic,
        // as long as it's semantially equivalent to linear)
        slv.setLogic("QF_UFNIA"); 
        this.slv = slv;
        this.integer = slv.getIntegerSort();
        this.bool = slv.getBooleanSort();
        this.productionTypes = productionTypes;
        this.universeNTSorts = new HashMap<String, Sort>();

        for (String nt : universeNTTypes.keySet()) {
            this.universeNTSorts.put(nt, eqTypeToSort(universeNTTypes.get(nt)));
        }
        
        this.state = new HashMap<String, Term>();
        
        // convert semFunctionTypes to cvc5 function sort
        for (String nt : semFunctionTypes.keySet()) {
            semFunctions.put(
                nt + ".Sem",
                slv.mkConst(
                    slv.mkFunctionSort(
                        Stream.of(semFunctionTypes.get(nt))
                            .map(type -> eqTypeToSort(type))
                            .toArray(Sort[]::new),
                        // output of semantics is same sort as nonterminal
                        universeNTSorts.get(nt)
                    ),
                    nt + ".Sem"
                )
            );
        }
    }

    public List<ProdSemantics> genNTSemantics(JsonNode node) throws CVC5ApiException {
        // to be returned
        List<ProdSemantics> semList = new ArrayList<ProdSemantics>();

        // list of inputs and output (e.g. [et, x, y, z, r])
        JsonNode inputoutput = node.get("definition").get("arguments");
        // map of inputs/output to their sorts (either integer or bool).
        // ignore first arg, which is the matched arg with nonterminal type (e.g. et)
        Map<String, Sort> inputoutputSorts = new HashMap<String, Sort>();

        for(int i = 1; i < inputoutput.size(); i++) {
            String sortString = node.get("rank").get("argumentSorts").get(i).asText();
            inputoutputSorts.put(inputoutput.get(i).asText(), strToSort(sortString));
        }
        // name of Nonterminal is first sort (e.g. "et E")
        String ntName = node.get("rank").get("argumentSorts").get(0).asText();

        // divide inputoutput into inputs and outputs (elsewhere in the json for some reason)
        List<Term> inputs = new ArrayList<Term>();
        Term output = null;
        for (JsonNode annotation : node.get("definition").get("body").get("annotations")) {
            if (annotation.get("keyword").get("name").asText().equals("input")) {
                // For input x of type Int, add mkConst(integer, "x")
                for (JsonNode var : annotation.get("value")) {
                    inputs.add(slv.mkConst(inputoutputSorts.get(var.asText()), var.asText()));
                }
            }
            else { // output
                JsonNode var = annotation.get("value").get(0);
                output = slv.mkConst(inputoutputSorts.get(var.asText()), var.asText());
            }
        }
        
        // loop thru productions
        for (JsonNode production : node.get("definition").get("body").get("binders")) {
            // make production-arg SMT vars (e.g. et1 and et2 for (+$ et1 et2))
            List<Term> args = new ArrayList<Term>();
            List<Term> semCalls = new ArrayList<Term>();

            // "operator" stores name of production - get list of arg types for this production
            String[] argStrings = productionTypes.get(ntName).get(production.get("operator").asText());

            int i = 0;
            for (JsonNode arg : production.get("arguments")) {
                Term argTerm = slv.mkConst(
                    // type of ith arg
                    universeNTSorts.get(argStrings[i]),
                    arg.asText()
                );
                args.add(argTerm);
                
                // make semantic application corresp to this arg (e.g. E.Sem(et1, x, y, z) for arg et1)
                Term[] semAppArgs = new Term[inputs.size() + 2];
                semAppArgs[0] = semFunctions.get(argStrings[i] + ".Sem");
                semAppArgs[1] = argTerm; //et1
                for(int input_i = 0; input_i < inputs.size(); input_i++) {
                    semAppArgs[input_i+2] = inputs.get(input_i);
                }

                semCalls.add(slv.mkTerm(Kind.APPLY_UF, semAppArgs));

                i++;
            }

            // Add inputs, output, and args to state so they are in scope for getProdSemanticsTerm
            for (Term input : inputs) {
                state.put(input.toString(), input);                
            }
            for (Term arg : args) {
                state.put(arg.toString(), arg);                
            }
            state.put(output.toString(), output);

            ProdSemantics sem = new ProdSemantics(ntName, inputs, output, args, getProdSemanticsTerm(production), semCalls);
            semList.add(sem);
            
            state.clear();
        }
        
        return semList;
    }       
    
    private Term getProdSemanticsTerm(JsonNode production) {
        return getChildSemantics(production.get("child"));
    }
    
    private Term getChildSemantics(JsonNode node) {
        if (!node.has("$termType")) {
            // no $termType means node is constant int or bool
            if (node.isInt())
                return slv.mkInteger(node.asInt());
            else if (node.isBoolean())
                return slv.mkBoolean(node.asBoolean());
            else 
                throw new RuntimeException("Invalid JsonNode: " + node.toString());
        }
        else {
            switch (node.get("$termType").asText()) {
                case ("exists"):
                    // add newly bound variables to state
                    for (JsonNode binding : node.get("bindings")) {
                        String var = binding.get("name").asText();
                        // new vars in Terms implicitly bound to exists
                        state.put(var, slv.mkConst(this.strToSort(binding.get("sort").asText()), var));
                    }
                    Term sem = getChildSemantics(node.get("child"));
                    // pop bound variables after exiting scope
                    for (JsonNode binding : node.get("bindings")) {
                        String var = binding.get("name").asText();
                        state.remove(var);
                    }
                    return sem;

                case ("application"):
                    // Call to nonterminal semantics (treated as uninterpreted function)
                    String namestr = node.get("name").asText();
                    if (namestr.contains(".Sem")) {
                        int numArgs = node.get("arguments").size();

                        // e.g. for E.sem(et1, x, y, z, r), ufArgs = [E.sem, et1, x, y, z] (r is return value)
                        Term[] ufArgs = new Term[numArgs];
                        // first argument for uninterpred function application is function itself
                        ufArgs[0] = semFunctions.get(namestr);
                        // fill in remaining ufArgs (the arguments of the UF in ufArgs[0]) with children
                        for(int i = 1; i < numArgs; i++) {
                            // i-1 because swapping r at end for E.sem at beginning
                            ufArgs[i] = getChildSemantics(node.get("arguments").get(i-1));
                        }
                        // Actual semantics is (= E.Sem(et1, x, y, z) r)
                        return slv.mkTerm(Kind.EQUAL, 
                            slv.mkTerm(Kind.APPLY_UF, ufArgs),
                            // r stored as last arg
                            getChildSemantics(node.get("arguments").get(numArgs-1))
                        );
                    }
                    // for some reason the constant Bools true and false parsed as applications
                    else if (namestr.equals("true")) {
                        return slv.mkBoolean(true);
                    }
                    else if (namestr.equals("false")) {
                        return slv.mkBoolean(false);
                    }
                    else {
                        // apply operation to recursively computed child terms
                        return slv.mkTerm(
                            strToKind(namestr),
                            StreamSupport.stream(node.get("arguments").spliterator(), false)
                                .map(arg -> getChildSemantics(arg))
                                .toArray(Term[]::new)
                        );
                    }

                case ("variable"):
                    return state.get(node.get("name").asText());
                    
                default:
                    throw new RuntimeException("Illegal $termType: " + node.get("$termType"));
            }
        }
    }
    
    
    // produce function Sort input x integer x integer x .. x integer -> output
    public static Sort naryFunction(Sort input, int arity, Sort output, Solver slv) {
        Sort[] args = new Sort[arity + 1];
        Arrays.fill(args, slv.getIntegerSort());
        args[0] = input;
        return slv.mkFunctionSort(args, output);
    }
    
    private Sort eqTypeToSort(EqType type) {
        switch (type) {
            case INT:
                return integer;
            case BOOL:
                return bool;
            default:
                throw new RuntimeException("Improper EqType to convert to Int/Bool sort: " + type.toString());
        }
    }
    
    private Sort strToSort(String str) {
        switch(str) {
            case "Int":
                return this.slv.getIntegerSort();
            case "Bool":
                return this.slv.getBooleanSort();
            default:
                // str could be nonterminal name - return sort of this nonterminal
                if (universeNTSorts.containsKey(str))
                    return universeNTSorts.get(str);
                throw new RuntimeException("Improper theory of integers sort: " + str);
        }        
    }
    
    private Kind strToKind(String str) {
        switch(str) {
            case "and":
                return Kind.AND;
            case "or":
                return Kind.OR;
            case "not":
                return Kind.NOT;
            case "=":
                return Kind.EQUAL;
            case "<=":
                return Kind.LEQ;
            case ">=":
                return Kind.GEQ;
            case ">":
                return Kind.GT;
            case "<":
                return Kind.LT;
            case "+":
                return Kind.ADD;
            case "*":
                return Kind.MULT;
            case "-":
                return Kind.SUB;
            default:
                throw new RuntimeException("Improper operator for theory of integers: " + str);
        }
    }
    
    
    /******************************************************************* */
    /* Tests against fixed CLIA semantics */
    /******************************************************************* */
    /* Integer types ------------------------------------------------------------------------- */
    public boolean isZero(ProdSemantics sem) {
        // needs zero args and returns int
        if (sem.args.size() != 0) {return false;}
        if (!sem.output.getSort().equals(integer)) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term assertions = slv.mkTerm(Kind.EQUAL, r_true, slv.mkInteger(0));
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }

    public boolean isOne(ProdSemantics sem) {
        // needs zero args and returns int
        if (sem.args.size() != 0) {return false;}
        if (!sem.output.getSort().equals(integer)) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term assertions = slv.mkTerm(Kind.EQUAL, r_true, slv.mkInteger(1));
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }

    public boolean isPlus(ProdSemantics sem) {
        // needs two args
        if (sem.args.size() != 2) {return false;}
        // verify types
        if (!(sem.args.get(0).getSort().equals(integer) 
              && sem.args.get(1).getSort().equals(integer)
              && sem.output.getSort().equals(integer))) {
            return false;
        }
        
        // clear solver
        slv.resetAssertions();
        
        // return term to be compared with sem.output
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term r1 = slv.mkConst(integer, "r1_true");
        Term r2 = slv.mkConst(integer, "r2_true");

        Term assertions = slv.mkTerm(Kind.AND,
            new Term[] {
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(0), r1), // E.sem(et1, x, y, z) = r1
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(1), r2), // E.sem(et2, x, y, z) = r2
                slv.mkTerm(Kind.EQUAL, slv.mkTerm(Kind.ADD, r1, r2), r_true)
            });
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }

    public boolean isIte(ProdSemantics sem) {
        // needs three args
        if (sem.args.size() != 3) {return false;}
        // verify types
        if (!(sem.args.get(0).getSort().equals(bool) 
              && sem.args.get(1).getSort().equals(integer)
              && sem.args.get(1).getSort().equals(integer)
              && sem.output.getSort().equals(integer))) {
            return false;
        }
        
        // clear solver
        slv.resetAssertions();
        
        // return term to be compared with sem.output
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term b1 = slv.mkConst(bool, "b1_true");
        Term b2 = slv.mkConst(bool, "b2_true");

        Term assertions = slv.mkTerm(Kind.OR,
            slv.mkTerm(Kind.AND,
                new Term[] {
                    slv.mkTerm(Kind.EQUAL, sem.semCalls.get(0), b1),
                    slv.mkTerm(Kind.EQUAL, b1, slv.mkBoolean(true)),
                    slv.mkTerm(Kind.EQUAL, sem.semCalls.get(1), r_true)
            }),
            slv.mkTerm(Kind.AND,
                new Term[] {
                    slv.mkTerm(Kind.EQUAL, sem.semCalls.get(0), b2),
                    slv.mkTerm(Kind.EQUAL, b2, slv.mkBoolean(false)),
                    slv.mkTerm(Kind.EQUAL, sem.semCalls.get(2), r_true)
            })
        );
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }
    
    /* Boolean types ------------------------------------------------------------------------- */
    public boolean isFalse(ProdSemantics sem) {
        // needs zero args and returns bool
        if (sem.args.size() != 0) {return false;}
        if (!sem.output.getSort().equals(bool)) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term assertions = slv.mkTerm(Kind.EQUAL, r_true, slv.mkBoolean(false));
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }

    public boolean isTrue(ProdSemantics sem) {
        // needs zero args and returns bool
        if (sem.args.size() != 0) {return false;}
        if (!sem.output.getSort().equals(bool)) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term assertions = slv.mkTerm(Kind.EQUAL, r_true, slv.mkBoolean(true));
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }

    public boolean isNot(ProdSemantics sem) {
        // needs zero args and returns bool
        if (sem.args.size() != 1) {return false;}
        if (!(sem.args.get(0).getSort().equals(bool)
              && sem.output.getSort().equals(bool))) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term rb = slv.mkConst(bool, "rb_true");

        Term assertions = slv.mkTerm(Kind.AND,
            new Term[] {
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(0), rb),
                slv.mkTerm(Kind.EQUAL, rb.notTerm(), r_true)
            });
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }
    
    public boolean isAnd(ProdSemantics sem) {
        // needs zero args and returns bool
        if (sem.args.size() != 2) {return false;}
        if (!(sem.args.get(0).getSort().equals(bool)
              && sem.args.get(1).getSort().equals(bool)
              && sem.output.getSort().equals(bool))) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term rb1 = slv.mkConst(bool, "rb1_true");
        Term rb2 = slv.mkConst(bool, "rb2_true");

        Term assertions = slv.mkTerm(Kind.AND,
            new Term[] {
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(0), rb1),
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(1), rb2),
                slv.mkTerm(Kind.EQUAL, slv.mkTerm(Kind.AND, rb1, rb2), r_true)
            });
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }

    public boolean isOr(ProdSemantics sem) {
        // needs zero args and returns bool
        if (sem.args.size() != 2) {return false;}
        if (!(sem.args.get(0).getSort().equals(bool)
              && sem.args.get(1).getSort().equals(bool)
              && sem.output.getSort().equals(bool))) {return false;}

        // clear solver
        slv.resetAssertions();
        Term r_true = slv.mkConst(universeNTSorts.get(sem.nonterminal), "r_true");
        Term rb1 = slv.mkConst(bool, "rb1_true");
        Term rb2 = slv.mkConst(bool, "rb2_true");

        Term assertions = slv.mkTerm(Kind.AND,
            new Term[] {
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(0), rb1),
                slv.mkTerm(Kind.EQUAL, sem.semCalls.get(1), rb2),
                slv.mkTerm(Kind.EQUAL, slv.mkTerm(Kind.OR, rb1, rb2), r_true)
            });
        
        // Add sem's semantics
        slv.assertFormula(slv.mkTerm(Kind.AND, assertions, sem.assertions));
        // unsat iff semantics are equivalent
        return slv.checkSatAssuming(slv.mkTerm(Kind.EQUAL, sem.output, r_true).notTerm()).isUnsat();
    }
}
