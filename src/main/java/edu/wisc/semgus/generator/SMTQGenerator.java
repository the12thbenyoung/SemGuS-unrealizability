/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */

package edu.wisc.semgus.generator;

import semirings.LinearSet;

import java.lang.Integer;
import java.util.Set;
import java.util.Vector;


public class SMTQGenerator {

    public enum Type {
        cvc4,
        cvc5,
        z3
    }

    // default to cvc5
    private static SmtGenerator generator = new SmtCvc5Generator();
    ;

    public static void initialize(Type type) {
        if (type == Type.cvc5) {
            generator = new SmtCvc5Generator();
        } else if (type == Type.z3) {
            generator = new edu.wisc.semgus.genenerator.SmtZ3Generator();
        } else {
            throw new RuntimeException("cannot make a " + type.toString() + " generator.");
        }
    }

    public static boolean checkSat(Vector<Integer> spec, Set<LinearSet> start) {
        return generator.checkSat(spec, start);
    }

    public static Set<Vector<Boolean>> getBVSet(Set<LinearSet> left, Set<LinearSet> right, String bop) {
        return generator.getBVSet(left, right, bop);
    }
}