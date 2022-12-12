package edu.wisc.semgus.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Vector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.Expression;
import io.github.cvc5.CVC5ApiException;

import edu.wisc.semgus.utilities.EqType;

public class SemgusParser {
    // Types (Int or Bool) of universe nonterminals
    Map<String, EqType> universeNTTypes;
    // Equations for each nonterminal
    Map<String, Equation> ntEquations;
    // holds NTTypes of production rules. e.g. {E -> {$0 -> [], $+ -> [E, E]}}
    Map<String, Map<String, String[]>> prodTypeMap;
    // map from nonterminals (e.g. E) to function sorts of their semantic function (e.g. E.Sem)
    Map<String, EqType[]> semFunctionTypes;
    // map from each nonterminal to prodSemantics of each of its productions
    Map<String, Map<String, ProdSemantics>> ntSemanticsMap;
    // map each input var to its list of values across examples e.g. {x -> [0,1], y -> [3,2]}
    Map<String, Vector<Integer>> exampleInputs;
    // ordered list of names of input vars, since "constraint" block doesn't include these
    Vector<String> inputVarNames;
    // Expect outputs for each example
    Vector<Integer> constraints;

    ProdSemanticsGenerator semGenerator;
    ObjectMapper objectMapper;

    public SemgusParser() {
        this.universeNTTypes = new HashMap<String, EqType>();
        this.ntEquations = new HashMap<String, Equation>();
        this.prodTypeMap = new HashMap<String, Map<String, String[]>>();
        this.semFunctionTypes = new HashMap<String, EqType[]>();
        this.ntSemanticsMap = new HashMap<String, Map<String, ProdSemantics>>();
        this.exampleInputs = new HashMap<String, Vector<Integer>>();
        this.inputVarNames = new Vector<String>();
        this.constraints = new Vector<Integer>();
        this.objectMapper = new ObjectMapper();
    }


    public ParsedGrammar grammarEqsFromSL(String slPath) throws IOException, CVC5ApiException {
        JsonNode rootNode = objectMapper.readTree(Files.readString(Paths.get(slPath)));

        for (JsonNode node : rootNode) {
            switch (node.get("$event").asText()) {
                case "define-term-type":
                    // Add entry to prodTypeMap for this nonterminal
                    HashMap<String, String[]> ntProdTypeMap = new HashMap<String, String[]>();
                    for (JsonNode production : node.get("constructors")) {
                        ProductionType prodType = objectMapper.treeToValue(production, ProductionType.class);
                        ntProdTypeMap.put(prodType.name, prodType.children);
                    }
                    prodTypeMap.put(node.get("name").asText(), ntProdTypeMap);
                    break;

                // Types (int or bool) of each background nonterminal is stored as last argument type in declare-function
                case "declare-function":
                    JsonNode argumentSorts = node.get("rank").get("argumentSorts");
                    String ntName = argumentSorts.get(0).asText();
                    // For CLIA, return type (type of r) must be Int or Bool
                    EqType return_type = strToType(argumentSorts.get(argumentSorts.size()-1).asText());
                    universeNTTypes.put(ntName, return_type);
                    
                    // convert argumentSorts e.g. [B, Int, Int, Int] to argTypesList [EqType.BOOL, EqType.INT, ...]
                    // last argumentSort is output, so ignore here
                    EqType[] argTypesList = new EqType[argumentSorts.size() - 1];
                    argTypesList[0] = universeNTTypes.get(ntName);
                    for(int i = 1; i < argumentSorts.size()-1; i++) {
                        argTypesList[i] = strToType(argumentSorts.get(i).asText());
                    }
                    semFunctionTypes.put(ntName, argTypesList);

                    break;
                    
                // defines semantics of all productions for a single nonterminal
                case "define-function":
                    semGenerator = new ProdSemanticsGenerator(prodTypeMap, universeNTTypes, semFunctionTypes);
                    Map<String, ProdSemantics> semList = semGenerator.genNTSemantics(node);                    
                    // stores "E.Sem", but want key to just be "E"
                    String nt = node.get("name").asText().replace(".Sem", "");
                    ntSemanticsMap.put(nt, semList);
                    
                    ArrayNode args = (ArrayNode) node.get("definition").get("arguments");
                    // add input variables (e.g. x,y,z) as keys to exampleInputs map
                    // and to inputVarNames, if haven't already done so
                    if (inputVarNames.size() == 0) {
                        // ignore first arg (e.g. et1 of type E) and last arg (return value r)
                        for(int i = 1; i < args.size() - 1; i++) {
                            exampleInputs.put(args.get(i).asText(), new Vector<Integer>());
                            inputVarNames.add(args.get(i).asText());
                        }
                    }
                    break;
                
                // Parser always stores grammar from synth-fun (if no new grammar is specified, it uses background grammar/theory
                // with "__agtt" appended to the end of each nonterminal)
                case "synth-fun":
                    parseSynthFun(node);
                    break;

                // Parse single constraint example
                case "constraint":
                    parseConstraint(node);
                    break;
            }
        }

        ParsedGrammar grammar = new ParsedGrammar();
        grammar.setNonterminalEquations(new ArrayList<Equation>(ntEquations.values()));
        grammar.setConstraints(constraints);
        grammar.setExampleInputs(exampleInputs);
        
        return grammar;
    }

    private void parseSynthFun(JsonNode node) throws IOException {
        Grammar grammar = objectMapper.treeToValue(node.get("grammar"), Grammar.class);

        // Universal nonterminal type of each nonterminal
        Map<String, String> parentNTs = new HashMap<String, String>();
        for (Map<String, String> obj : grammar.nonTerminals) {
            parentNTs.put(obj.get("name").toString(), obj.get("termType").toString());                   
        }

        for (Production production : grammar.productions) {
            // name of nonterminal
            String nt = production.instance;
            // name of operator. get rid of "__agtt" that gets appended when default background grammar is used
            String op = production.operator.replace("__agtt", "");
            EqType type = universeNTTypes.get(parentNTs.get(nt));
            Expression newExp;
            if (type == EqType.INT) {
                newExp = Expression.inferIntExpression(
                    op, 
                    production.occurrences, 
                    ntSemanticsMap.get(parentNTs.get(nt)).get(op),
                    semGenerator
                );
            }
            else {// (type == EqType.BOOL)
                newExp = Expression.inferBoolExpression(
                    op, 
                    production.occurrences, 
                    ntSemanticsMap.get(parentNTs.get(nt)).get(op), 
                    semGenerator
                );
            }

            if (!ntEquations.containsKey(nt)) 
                ntEquations.put(nt, new Equation(nt, newExp, type));
            else 
                ntEquations.get(nt).right = ntEquations.get(nt).right.oplus(newExp);
        }
    }

    private void parseConstraint(JsonNode node) {
        ArrayNode array = (ArrayNode) node.get("constraint").get("arguments");
        // name of nt
        String nt = array.get(0).get("returnSort").asText();

        // first size-1 arguments are inputs, last is expected output
        for (int i = 1; i < array.size()-1; i++) {
            if (array.get(i).isInt()) {
                // add as new example for (i-1)st input var
                exampleInputs.get(inputVarNames.get(i-1)).add(array.get(i).asInt());
            }
        }
        constraints.add(array.get(array.size() - 1).asInt());
    }
    
    public static EqType strToType(String str) {
        switch (str) {
            case "Int":
                return EqType.INT;
            case "Bool":
                return EqType.BOOL;
            default:
                throw new RuntimeException("Invalid nonterminal type for CLIA: " + str);
        }
    }
}

class Production {
    public String instance;
    public String operator;
    public String[] occurrences;
}

class Grammar {
    public Map<String, String>[] nonTerminals;
    public List<Production> productions; 
}

class ProductionType {
    public String name;
    public String[] children;
}