package edu.wisc.semgus;

import java.io.IOException;
import edu.wisc.semgus.parser.SemgusParser;
import edu.wisc.semgus.Sygus2Semgus.GrammarNode;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // SemgusParser parser = new SemgusParser();
        // System.out.println(parser.grammarEqsFromSL("parser/test_grammar.json"));
        
        GrammarNode g = GrammarNode.parseToGrammarNode("parser/sygus/grammar.sl");
        System.out.println(g);
    }
}
