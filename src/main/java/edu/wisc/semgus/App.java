package edu.wisc.semgus;

import java.io.IOException;

import org.antlr.v4.runtime.atn.SemanticContext;

import edu.wisc.semgus.Sygus2Semgus.Sygus2Semgus;
import edu.wisc.semgus.parser.SemgusParser;
import io.github.cvc5.*;

public class App 
{
    public static void main( String[] args ) throws IOException, CVC5ApiException
    {
        SemgusParser parser = new SemgusParser();
        System.out.println(parser.grammarEqsFromSL("parser/test_grammar.json").getNonterminalEquations());
        // Sygus2Semgus converter = new Sygus2Semgus();
        // converter.sygusSL2SemgusSL("parser/test_grammar.json");
        // cvc5_test.main(null);
    }
}
