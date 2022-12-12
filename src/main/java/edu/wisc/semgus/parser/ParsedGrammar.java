package edu.wisc.semgus.parser;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import edu.wisc.semgus.utilities.Equation;

/**
 * Class to represent values extracted after parsing grammer file
**/
public class ParsedGrammar {
    private List<Equation> nonterminalEquations;
    // e.g. {x -> [0,1], y -> [3,2]}
    private Map<String, Vector<Integer>> exampleInputs;
    // expected output of each example
    private Vector<Integer> constraints;

    public void setNonterminalEquations(List<Equation> nonterminalEquations) {
        this.nonterminalEquations = nonterminalEquations;
    }

    public List<Equation> getNonterminalEquations() {
        return this.nonterminalEquations;
    }

    public void setConstraints(Vector<Integer> constraints) {
        this.constraints = constraints;
    }

    public Vector<Integer> getConstraints() {
        return this.constraints;
    }
    
    public Map<String, Vector<Integer>> getExampleInputs() {
        return this.exampleInputs;
    }
    
    public void setExampleInputs(Map<String, Vector<Integer>> exampleInputs) {
        this.exampleInputs = exampleInputs;
    }
}