package edu.wisc.semgus;

import edu.wisc.semgus.fixedpoint.Newton;
import edu.wisc.semgus.fixedpoint.IteFixedPointSolver;
import edu.wisc.semgus.fixedpoint.LinearSet;
import edu.wisc.semgus.generator.SMTQGenerator;
import edu.wisc.semgus.parser.SemgusParser;
import edu.wisc.semgus.parser.ParsedGrammar;
import edu.wisc.semgus.utilities.Equation;
import io.github.cvc5.CVC5ApiException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.lang.model.util.ElementScanner6;

public class SLMain {
    public static void main(String args[]) throws IOException, CVC5ApiException {
	// with or without optimization
        if(args.length>1 && args[1].equals("noOpt")) {
            Newton.opt = false;
            IteFixedPointSolver.opt = false;
        }

	// cvc4 or z3 as SMT solver
        if(args.length>1 && args[1].equals("z3")) {
            SMTQGenerator.initialize(SMTQGenerator.Type.z3);
        } else if (args.length>3 && args[1].equals("cvc4")){
            SMTQGenerator.initialize(SMTQGenerator.Type.cvc4);
        } else { // default to cvc5
            SMTQGenerator.initialize(SMTQGenerator.Type.cvc5);
        }

        SemgusParser parser = new SemgusParser();

	// build the term equations from json
    ParsedGrammar grammar = parser.grammarEqsFromSL(args[0]);
    // ParsedGrammar grammar = parser.grammarEqsFromSL("benchmarks/clia-unequiv-sem/sample/sample.json");
    List<Equation> termEqs = grammar.getNonterminalEquations();
    Map<String, Vector<Integer>> inputExMap = grammar.getExampleInputs();
    Vector<Integer> spec = grammar.getConstraints();
    System.out.println(inputExMap);
    System.out.println(spec);
    System.out.println(termEqs);

    PrintStream original = System.out;
	
	// dump problem size
        // System.out.print(grammarNode.getNtNodes().size()+" & "+totalTrans+ " & "+inputExMap.keySet().size()+" & "+numEx+" & ");
        // System.setOut(new PrintStream(new OutputStream() {            public void write(int b) {                          }        }));
        float startTime_sl = System.nanoTime();

	// solve the semi-linear set
        Map<String,Set<LinearSet>> solution =  IteFixedPointSolver.SolveIteFixedPoint(termEqs,inputExMap);
        
        String startingNT;
        // assume starting nonterminal is either S or @S__agtt or "Start" (for custom semantics)
        if (solution.keySet().contains(grammar.startingNT))
            startingNT = grammar.startingNT;
        else if (solution.keySet().contains("@" + grammar.startingNT + "__agtt"))
            startingNT = "@" + grammar.startingNT + "__agtt";
        else if (solution.keySet().contains("Start"))
            startingNT = "Start";
        else if (solution.keySet().contains("start"))
            startingNT = "start";
        else
            throw new RuntimeException("Improper root/starting nonterminal");

        float endTime_sl = System.nanoTime();
        float timeElapsed_sl = endTime_sl - startTime_sl;
	
	// parse solution and dump result
        int solutionSize = solution.get(startingNT).size();

        float avgPeriod = 0;
        int periodCount = 0;
        for(LinearSet ls:solution.get(startingNT)){
            if(avgPeriod == 0)
                avgPeriod = ls.getPeriod().size();
            avgPeriod = avgPeriod*periodCount+ls.getPeriod().size();
            periodCount++;
            avgPeriod = avgPeriod/periodCount;
        }


        System.setOut(original);

        float startTime_smt = System.nanoTime();
        Boolean result = SMTQGenerator.checkSat(spec,solution.get(startingNT));

        System.out.println(result);

        float endTime_smt = System.nanoTime();
        float timeElapsed_smt = endTime_smt - startTime_smt;
        // System.out.print(String.format ("%.2f",(timeElapsed_smt/1000000000))
        // + " & "+String.format ("%.2f",((timeElapsed_sl+timeElapsed_smt)/1000000000))+" & "+result+"\\\\");
    }
}
