package edu.wisc.semgus.parser;

import java.util.List;
import java.util.Map;

import edu.wisc.semgus.utilities.Equation;

/**
 * Class to represent values extracted after parsing grammer file
**/
public class ParsedGrammer {
    private List<Equation> nonterminalEquations;
    private Map<String, List<Integer>> constraints;

    public void setNonterminalEquations(List<Equation> nonterminalEquations) {
        this.nonterminalEquations = nonterminalEquations;
    }

    public List<Equation> getNonterminalEquations() {
        return this.nonterminalEquations;
    }

    public void setConstraints(Map<String, List<Integer>> constraints) {
        this.constraints = constraints;
    }

    public Map<String, List<Integer>> getConstraints() {
        return this.constraints;
    }
}