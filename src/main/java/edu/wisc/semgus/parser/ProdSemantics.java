package edu.wisc.semgus.parser;

import io.github.cvc5.Term;
import java.util.List;
public class ProdSemantics {

    /*  e.g. consider this production
    (($+ et1 et2)
     (exists ((r1 Int) (r2 Int))
         (and
          (E.Sem et1 x y z r1)
          (E.Sem et2 x y z r2)
          (= r (+ r1 r2)))))
    */
    // name of nonterminal
    public String nonterminal;
    // Columns/fields of examples (x, y, z)
    public List<Term> inputs;
    // Return variable of production (r)
    public Term output;
    // Arguments to production rule (et1, et2)
    public List<Term> args;
    // Conjunction of semantic-defining assertions
    public Term assertions;
    
    public ProdSemantics(String nonterminal, List<Term> inputs, Term output, List<Term> args, Term assertions) {
        this.nonterminal = nonterminal;
        this.inputs = inputs;
        this.output = output;
        this.args = args;
        this.assertions = assertions;
    }
}