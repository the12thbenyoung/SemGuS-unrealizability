/* Convert CLIA-like SyGuS grammar specifications to SemGuS specifications with
 * (almost) fixed background CLIA background theory
 */
package edu.wisc.semgus.Sygus2Semgus;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;

public class Sygus2Semgus {
    // search for non-0-1 integer constants in sygus grammar
    
    public static void main( String[] args ) throws FileNotFoundException {
        Sygus2Semgus s2s = new Sygus2Semgus();
        s2s.sygusSL2SemgusSL("parser/sygus/grammar.sl");
    }
    
    public Sygus2Semgus() {}
    
    public void sygusSL2SemgusSL(String grammarPath) throws FileNotFoundException {
        GrammarNode gNode = GrammarNode.parseToGrammarNode("parser/sygus/grammar.sl");
        System.out.println(gNode);
        // search for integer constants in grammar
        Set<Integer> constants = new HashSet<Integer>();
        for (NTNode node : gNode.ntNodes) {
            for (RuleNode rule : node.rules) {
                // no children -> terminal/leaf rule
                try {
                    if (rule.content.children.size() == 0) {
                        try {
                            int constant = Integer.parseInt(rule.content.symbol);
                            if (constant != 0 && constant != 1)
                                constants.add(constant);
                        } catch (NumberFormatException e) {
                            continue;
                        } catch (NullPointerException e) {
                            continue;
                        }
                    }
                } catch (NullPointerException e) {
                    try {
                        int constant = Integer.parseInt(rule.content.symbol);
                        if (constant != 0 && constant != 1)
                            constants.add(constant);
                    } catch (NumberFormatException e2) {
                        continue;
                    } catch (NullPointerException e2) {
                        continue;
                    }
                }
            }
        }

        PrintWriter writer = new PrintWriter("parser/output_grammar.sl");
        writer.printf(String.join(System.getProperty("line.separator"),
            "(declare-term-types",
            "((E 0) (B 0))",
            "(",
            "\t( ; E productions",
            "\t\t($x)",
            "\t\t($y)",
            "\t\t($0)",
            "\t\t($1)\n"
        ));
        // Add new productions for each integer constant
        for (int c : constants) {
            writer.printf("\t\t($%d)\n", c);
        }
        
        writer.printf(String.join(System.getProperty("line.separator"),
            "\t\t($+ E E)",
            "\t\t($ite B E E)",
            "\t)",
            "\t(  ; B productions",
            "\t\t($t)",
            "\t\t($f)",
            "\t\t($not B)",
            "\t\t($and B B)",
            "\t\t($or B B)",
            "\t\t($>= E E)",
            "\t)",
            ")",
            ")",
            "(define-funs-rec\n"
        ));

        // Nonterminal signatures
        writer.printf(String.join(System.getProperty("line.separator"),
            "\t((E.sem ((et E) " + gNode.argList_string.substring(0, gNode.argList_string.length()-1) + "(r Int)) Bool)",
            "\t((B.sem ((et B) " + gNode.argList_string.substring(0, gNode.argList_string.length()-1) + "(r Bool)) Bool))\n"
        ));
        
        //TODO: finish this (maybe? Seems like it's already been done)
        writer.close();
    }
}
