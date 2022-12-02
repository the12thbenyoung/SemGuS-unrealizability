package edu.wisc.semgus.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.lang.model.util.ElementScanner6;

import edu.wisc.semgus.utilities.EqType;

import io.github.cvc5.*;
public class ProdSemanticsGenerator {
    // e.g. {E -> {+$ -> [E, E]}} to say production +$ from E takes two E args
    private Map<String, Map<String, String[]>> productionTypes;
    // e.g. {E -> INT, B -> BOOL}
    private Map<String, EqType> universeNTTypes;
    private Solver slv;
    // Map from var/const names to CVC5 Terms currently in scope when recursively parsing semantics
    private Map<String, Term> state;
    // Sorts of each nonterminal's semantics - e.g. B.sem(bt, x, y, z) -> r has function sort Bool x Int x Int x Int -> Bool
    private Map<String, Sort> semFunctionSorts = new HashMap<String, Sort>();

    public ProdSemanticsGenerator(Map<String, Map<String, String[]>> productionTypes, 
                                  Map<String, EqType> universeNTTypes, 
                                  Map<String, EqType[]> semFunctionTypes) 
    throws CVC5ApiException {
        this.productionTypes = productionTypes;
        this.universeNTTypes = universeNTTypes;

        Solver slv = new Solver();
                
        slv.setOption("produce-models", "true"); // Produce Models
        slv.setOption("dag-thresh", "0"); // Disable dagifying the output
        slv.setOption("output-language", "smt2"); // use smt-lib v2 as output language
        // quantifier-free uninterpreted functions + nonlinear arithmetic (custom semantics can use nonlinear arithmetic,
        // as long as it's semantially equivalent to linear)
        slv.setLogic("QF_UFNIA"); 
        this.slv = slv;
        
        this.state = new HashMap<String, Term>();
        
        // convert semFunctionTypes to cvc5 function sort
        for (String nt : semFunctionTypes.keySet()) {
            semFunctionSorts.put(
                nt + ".Sem", 
                slv.mkFunctionSort(
                    Stream.of(semFunctionTypes.get(nt))
                        .map(type -> eqTypeToSort(type))
                        .toArray(Sort[]::new),
                    // output of semantics is same sort as nonterminal
                    eqTypeToSort(universeNTTypes.get(nt))
                )
            );
        }
    }
    // private static ObjectMapper objectMapper = new ObjectMapper();

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
            // "operator" stores name of production - get list of arg types for this production
            String[] argStrings = productionTypes.get(ntName).get(production.get("operator").asText());

            int i = 0;
            for (JsonNode arg : production.get("arguments")) {
                args.add(slv.mkConst(
                    // type of ith arg
                    eqTypeToSort(universeNTTypes.get(argStrings[i])),
                    arg.asText()
                ));
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

            ProdSemantics sem = new ProdSemantics(ntName, inputs, output, args, getProdSemanticsTerm(production));
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
                        ufArgs[0] = slv.mkConst(semFunctionSorts.get(namestr), namestr);
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
    
    // produce function Sort input x input x ... x input -> output
    public static Sort naryFunction(Sort input, int arity, Sort output, Solver slv) {
        Sort[] args = new Sort[arity];
        Arrays.fill(args, input);
        return slv.mkFunctionSort(args, output);
    }
    
    private Sort eqTypeToSort(EqType type) {
        switch (type) {
            case INT:
                return slv.getIntegerSort();
            case BOOL:
                return slv.getBooleanSort();
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
                if (universeNTTypes.containsKey(str))
                    return eqTypeToSort(universeNTTypes.get(str));
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
}
