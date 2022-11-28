/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */

package edu.wisc.semgus.genenerator;

import semirings.LinearSet;

import java.util.Set;
import java.util.Vector;

public interface SmtGenerator {
    boolean checkSat(Vector<Integer> spec, Set<LinearSet> start);

    Set<Vector<Boolean>> getBVSet(Set<LinearSet> left, Set<LinearSet> right, String bop);
}
