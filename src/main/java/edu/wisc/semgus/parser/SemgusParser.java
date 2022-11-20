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

import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.Expression;
import edu.wisc.semgus.utilities.EqType;

public class SemgusParser 
{
    public SemgusParser() {
    }

    public List<Equation> grammarEqsFromSL(String slPath) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(Files.readString(Paths.get(slPath)));

        // Array of all universe/background theory nonterminals
        ArrayList<String> universeNonterminals = new ArrayList<String>();
        // Types (Int or Bool) of universe nonterminals
        Map<String, EqType> universeNTTypes = new HashMap<String, EqType>();
        // Equations for each nonterminal
        Map<String, Equation> nonterminalEquations = new HashMap<String, Equation>();
        for (JsonNode node : rootNode) {
            // Background nonterminal declarations
            if (node.get("$event").asText().equals("declare-term-type")) {
                universeNonterminals.add(node.get("name").asText());
            }
            
            // Types (int or bool) of each background nonterminal is stored as last argument type in declare-function
            if (node.get("$event").asText().equals("declare-function")) {
                JsonNode argumentSorts = node.get("rank").get("argumentSorts");
                universeNTTypes.put(argumentSorts.get(0).asText(), 
                                    argumentSorts.get(argumentSorts.size()-1).asText().equals("Int") ? EqType.INT : EqType.BOOL);
            }
            
            // Parser always stores grammar from synth-fun (if no new grammar is specified, it uses background grammar/theory
            // with "__agtt" appended to the end of each nonterminal)
            if (node.get("$event").asText().equals("synth-fun")) {
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
        }
        
        return new ArrayList<Equation>(nonterminalEquations.values());
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