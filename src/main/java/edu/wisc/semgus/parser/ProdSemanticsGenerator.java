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

import edu.wisc.semgus.utilities.EqType;

import io.github.cvc5.*;
public class ProdSemanticsGenerator {
    private Map<String, Map<String, String[]>> productionTypes;
    private Map<String, EqType> universeNTTypes;
    public ProdSemanticsGenerator(Map<String, Map<String, String[]>> productionTypes, Map<String, EqType> universeNTTypes) {
        this.productionTypes = productionTypes;
        this.universeNTTypes = universeNTTypes;
    }
    // private static ObjectMapper objectMapper = new ObjectMapper();

    public List<ProdSemantics> genNTSemantics(JsonNode node) throws CVC5ApiException {
        Solver slv = new Solver();
                
        slv.setOption("produce-models", "true"); // Produce Models
        slv.setOption("dag-thresh", "0"); // Disable dagifying the output
        slv.setOption("output-language", "smt2"); // use smt-lib v2 as output language
        slv.setLogic("QF_UFLIRA"); // quantifier-free uninterpreted functions + LIA

        // Sorts
        Sort integer = slv.getIntegerSort();
        Sort bool = slv.getBooleanSort();

        List<ProdSemantics> semList = new ArrayList<ProdSemantics>();
        // list of arguments (e.g. [et, x, y, z, r])
        JsonNode args = node.get("definition").get("arguments");
        // map of args to their sorts (either integer or bool).
        // ignore first arg, which is the matched arg with nonterminal type (e.g. et)
        Map<String, Sort> argSorts = new HashMap<String, Sort>();

        for(int i = 1; i < args.size(); i++) {
            Sort sort;
            String sortString = node.get("rank").get("argumentSorts").get(i).asText();
            switch (sortString) {
                case "Int":
                    sort = integer;
                    break;
                case "Bool":
                    sort = bool;
                    break;
                default:
                    throw new RuntimeException("Improper argument type: " + sortString);
            }
            argSorts.put(args.get(i).asText(), sort);
        }
        // input and output args stored elsewhere in json for some reason
        List<Term> inputs = new ArrayList<Term>();
        Term output;
        for (JsonNode annotation : node.get("definition").get("body").get("annotations")) {
            if (annotation.get("keyword").get("name").asText().equals("input")) {
                // For input x of type Int, add mkConst(integer, "x")
                for (JsonNode var : annotation.get("value")) {
                    inputs.add(slv.mkConst(argSorts.get(var.asText()), var.asText()));
                }
            }
            else { // output
                JsonNode var = annotation.get("value").get(0);
                output = slv.mkConst(argSorts.get(var.asText()), var.asText());
            }
        }
        
        // loop thru productions
        // for (JsonNode production : node.get("definition").get("body").get("binders")) {
            // List 
            // ProdSemantics sem = new ProdSemantics(null, inputs, output, inputs, output)
            // semList.add(sem);
        // }
        
        return semList;
    }       
    
    // TODO
    // private static Term genProdSemantics(JsonNode root, Solver slv) {
    // }
    
    // produce function Sort input x input x ... x input -> output
    public static Sort naryFunction(Sort input, int arity, Sort output, Solver slv) {
        Sort[] args = new Sort[arity];
        Arrays.fill(args, input);
        return slv.mkFunctionSort(args, output);
    }
}
