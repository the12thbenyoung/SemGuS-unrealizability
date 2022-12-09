package edu.wisc.semgus;

import java.io.IOException;

import org.antlr.v4.runtime.atn.SemanticContext;

import edu.wisc.semgus.Sygus2Semgus.Sygus2Semgus;
import edu.wisc.semgus.parser.SemgusParser;
import edu.wisc.semgus.parser.cvc5_test;
import edu.wisc.semgus.parser.ProdSemanticsGenerator;
import io.github.cvc5.*;

public class App 
{
    public static void main( String[] args ) throws IOException, CVC5ApiException
    {
        SemgusParser parser = new SemgusParser();
        parser.grammarEqsFromSL("parser/test_grammar.json").getNonterminalEquations();
    }
}
