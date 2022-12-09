package edu.wisc.semgus.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.Expression;
import io.github.cvc5.CVC5ApiException;

import edu.wisc.semgus.utilities.EqType;

public class SemgusParser {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public ParsedGrammer grammarEqsFromSL(String slPath) throws IOException, CVC5ApiException {
        JsonNode rootNode = objectMapper.readTree(Files.readString(Paths.get(slPath)));

        // Array of all universe/background theory nonterminals
        List<String> universeNonterminals = new ArrayList<String>();
        // Types (Int or Bool) of universe nonterminals
        Map<String, EqType> universeNTTypes = new HashMap<String, EqType>();
        // Equations for each nonterminal
        Map<String, Equation> nonterminalEquations = new HashMap<String, Equation>();
        // Values of example constraints
        Map<String, List<Integer>> constraints = new HashMap<>();
        ProdSemanticsGenerator semGenerator;
        // holds NTTypes of production rules. e.g. {E -> {$0 -> [], $+ -> [E, E]}}
        Map<String, Map<String, String[]>> prodTypeMap = new HashMap<String, Map<String, String[]>>();
        // map from nonterminals (e.g. E) to function sorts of their semantic function (e.g. E.Sem)
        Map<String, EqType[]> semFunctionTypes = new HashMap<String, EqType[]>();
        
        for (JsonNode node : rootNode) {
            switch (node.get("$event").asText()) {
                // Background nonterminal declarations
                case "declare-term-type":
                    universeNonterminals.add(node.get("name").asText());
                    break;
                    
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
                    
                case "define-function":
                    semGenerator = new ProdSemanticsGenerator(prodTypeMap, universeNTTypes, semFunctionTypes);
                    List<ProdSemantics> semList = semGenerator.genNTSemantics(node);                    
                    for (ProdSemantics sem : semList) {
                        System.out.println(sem.assertions);
                        System.out.println(semGenerator.isOr(sem));
                        System.out.println("===========================");
                    }
                    break;
                
                // Parser always stores grammar from synth-fun (if no new grammar is specified, it uses background grammar/theory
                // with "__agtt" appended to the end of each nonterminal)
                case "synth-fun":
                    parseSynthFun(node, universeNTTypes, nonterminalEquations);
                    break;

                // Parse single constraint example
                case "constraint":
                    parseConstraint(node, constraints);
                    break;
            }
        }

        ParsedGrammer parsedGrammer = new ParsedGrammer();
        parsedGrammer.setNonterminalEquations(new ArrayList<Equation>(nonterminalEquations.values()));
        parsedGrammer.setConstraints(constraints);
        
        return parsedGrammer;
    }

    private void parseSynthFun(JsonNode node, Map<String, EqType> universeNTTypes, Map<String, Equation> nonterminalEquations) throws IOException {
        Grammar grammar = objectMapper.treeToValue(node.get("grammar"), Grammar.class);

        // Universal nonterminal type of each nonterminal
        Map<String, String> parentNTs = new HashMap<String, String>();
        for (Map<String, String> obj : grammar.nonTerminals) {
            parentNTs.put(obj.get("name").toString(), obj.get("termType").toString());                   
        }

        for (Production production : grammar.productions) {
            String nonterminal = production.instance;
            EqType type = universeNTTypes.get(parentNTs.get(nonterminal));
            Expression newExp;
            if (type == EqType.INT)
                newExp = Expression.inferIntExpression(production.operator, production.occurrences);
            else // (type == EqType.BOOL)
                 newExp = Expression.inferBoolExpression(production.operator, production.occurrences);

            if (!nonterminalEquations.containsKey(nonterminal)) 
                nonterminalEquations.put(nonterminal, new Equation(nonterminal, newExp, type));
            else 
                nonterminalEquations.get(nonterminal).right = nonterminalEquations.get(nonterminal).right.oplus(newExp);
        }
    }

    private void parseConstraint(JsonNode node, Map<String, List<Integer>> constraints) {
        ArrayNode array = (ArrayNode) node.get("constraint").get("arguments");
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).isInt()) {
                if (constraints.get(String.valueOf(i)) == null) {
                    constraints.put(String.valueOf(i), new ArrayList());
                }
                constraints.get(String.valueOf(i)).add(array.get(i).intValue());
            }
        }
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