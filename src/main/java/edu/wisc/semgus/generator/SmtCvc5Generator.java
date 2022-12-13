/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */

package edu.wisc.semgus.generator;

import io.github.cvc5.Kind;
import io.github.cvc5.Result;
import io.github.cvc5.Solver;
import io.github.cvc5.Sort;
import io.github.cvc5.Term;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import edu.wisc.semgus.fixedpoint.LinearSet;

public class SmtCvc5Generator implements edu.wisc.semgus.generator.SmtGenerator {

    @Override
    public boolean checkSat(Vector<Integer> spec, Set<LinearSet> start) {
        Iterator<LinearSet> iterator = start.iterator();

        while (iterator.hasNext()) {
            if (checkInLinearSet(spec, iterator.next())) {
                //System.out.print(spec+" "+ls);
                return true;
            }
            iterator.remove();
            //System.out.println(" start next Q");
        }
        return false;
    }

    public static boolean checkInLinearSet(Vector<Integer> target, LinearSet ls) {
        Solver solver = new Solver();

        boolean result = true;
        if (ls.getPeriod().size() == 0) { // simple mode
            for (int i = 0; i < target.size(); i++) {
                result = result && (ls.getBase().get(i) == target.get(i));
            }
            return result;
        }

        Sort intSort = solver.getIntegerSort();
        Term zero = solver.mkInteger(0);
        Term body = solver.mkTrue();
        List<Term> bound_vars = new ArrayList<>();

        for (int i = 0; i < ls.getPeriod().size(); i++) {
            Term var = solver.mkConst(intSort, "z" + "_" + i);
            bound_vars.add(var);
            body = body.andTerm(solver.mkTerm(Kind.GEQ, var, zero));
        }

        List<Vector<Integer>> periodList = new ArrayList<>(ls.getPeriod());

        for (int j = 0; j < target.size(); j++) {
            Term poly = solver.mkReal(ls.getBase().get(j));
            for (int i = 0; i < ls.getPeriod().size(); i++) {
                Term value = solver.mkReal(periodList.get(i).get(j));
                Term factor = solver.mkTerm(Kind.MULT, bound_vars.get(i), value);
                poly = solver.mkTerm(Kind.ADD, poly, factor);
            }
            Term value = solver.mkReal(target.get(j));
            Term equation = solver.mkTerm(Kind.EQUAL, poly, value);
            body = body.andTerm(equation);
        }

        solver.assertFormula(body);
        return solver.checkSat().isSat();
    }


    @Override
    public Set<Vector<Boolean>> getBVSet(Set<LinearSet> left, Set<LinearSet> right, String bop) {
        Set<Vector<Boolean>> result = new HashSet<>();
        List<LinearSet> leftList = new ArrayList<>(left);
        List<LinearSet> rightList = new ArrayList<>(right);
        if(leftList.size() == 0 || rightList.size() == 0)
            return result;

        int dim = leftList.get(0).getBase().size();
        Vector<Boolean> currentBv = new Vector();
        return getBVSet_recursion(leftList,rightList,bop,dim,result,currentBv);
    }

    private static Set<Vector<Boolean>> getBVSet_recursion(List<LinearSet> left, List<LinearSet> right, String bop, int remain, Set<Vector<Boolean>> result, Vector<Boolean> currentBv) {

        for(LinearSet leftLS:left){
            for(LinearSet rightLS:right){
                result.addAll(getBVSet_recursion_2(leftLS, rightLS, bop, remain, result, currentBv));

                if(result.size()==Math.pow(2,left.get(0).getBase().size()))
                    return result;
            }
        }
        return result;
    }

    private static Set<Vector<Boolean>> getBVSet_recursion_2(LinearSet left, LinearSet right, String bop, int remain, Set<Vector<Boolean>> result, Vector<Boolean> currentBv) {
        if(remain != 0) {
            Vector<Boolean> currentBv_T = new Vector(currentBv);
            currentBv_T .add(true);
            Vector<Boolean> currentBv_F = new Vector(currentBv);
            currentBv_F.add(false);
            result.addAll(getBVSet_recursion_2(left, right, bop, remain-1, result, currentBv_T));
            result.addAll(getBVSet_recursion_2(left, right, bop, remain-1, result, currentBv_F));
            return  result;
        }

        if(result.contains(currentBv))
            return result;

        int dim = currentBv.size();
        if(result.size()==Math.pow(2,dim))
            return result;


        if(left.getPeriod().size() == 0 && right.getPeriod().size() == 0){ // simple mode
            boolean flag = true;
            for(int i = 0; i < dim;i++){
                if(currentBv.get(i))
                    flag = flag && parse_bop_simple(bop, left.getBase().get(i),right.getBase().get(i));
                else
                    flag = flag && (!parse_bop_simple(bop,left.getBase().get(i),right.getBase().get(i)));
            }
            if (flag)
                result.add(currentBv);
            return result;

        }

        Solver solver = new Solver();
        Sort intSort = solver.getIntegerSort();
        //       Term trueTerm = solver.mkTrue();

        // body = true /\ (z = bx1 + x*px1 \/ z = bx2 + x*px2 \/ ...) /\ (z bop by1 + y*py1 \/ ..)
        //                        z \in left                                   z bop z' \in right

        List<Term> boundList_X = new ArrayList<>();
        List<Term> boundList_Y = new ArrayList<>();

        Term zero = solver.mkReal(0);
        Term body = solver.mkTrue();

        List<Vector<Integer>> period_list_x = new ArrayList<>(left.getPeriod());
        List<Vector<Integer>> period_list_y = new ArrayList<>(right.getPeriod());

        for(int ix = 0; ix < left.getPeriod().size(); ix++ ) {
            Term var = solver.mkConst(intSort, "x_"+ix+"_"+"d");
            boundList_X.add(var);
            solver.assertFormula(solver.mkTerm(Kind.GEQ, var, zero));
        }

        for(int iy = 0; iy < right.getPeriod().size(); iy++  ){
            Term var = solver.mkConst(intSort, "y_"+iy+"_"+"d");
            boundList_Y.add(var);
            solver.assertFormula(solver.mkTerm(Kind.GEQ, var, zero));
        }

        for(int d = 0; d < dim; d++){
            Term body_left = solver.mkReal(left.getBase().get(d));
            Term body_right =solver.mkReal(right.getBase().get(d));

            for(int i = 0; i < period_list_x.size(); i++){
                Term value = solver.mkReal(period_list_x.get(i).get(d));
                Term mult = solver.mkTerm(Kind.MULT, boundList_X.get(i), value);
                body_left = solver.mkTerm(Kind.ADD, body_left, mult);
            }
            for(int i = 0; i < period_list_y.size(); i++){
                Term value = solver.mkReal(period_list_y.get(i).get(d));
                Term mult = solver.mkTerm(Kind.MULT, boundList_Y.get(i), value);
                body_right = solver.mkTerm(Kind.ADD, body_right, mult);
            }

            Term bopTerm = solver.mkTerm(parse_bop(bop), body_left, body_right);
            if(currentBv.get(d) == false) {
                bopTerm = bopTerm.notTerm();
            }

            body = body.andTerm(bopTerm);
        }
        solver.assertFormula(body);

        if(solver.checkSat().isSat()) {
            // System.out.println("SAT: "+currentBv+" "+body);
            result.add(currentBv);
        }

        return result;
    }

    private static Kind parse_bop(String bop){
        switch (bop){
            case "<":return Kind.LT;
            case ">":return Kind.GT;
            case "<=":return Kind.LEQ;
            case ">=":return Kind.GEQ;
            case "=":
            case "==":return Kind.EQUAL;
        }
        return  null;
    }

    private static boolean parse_bop_simple(String bop, Integer left, Integer right){
        switch (bop){
            case "<":return left < right;
            case ">":return left > right;
            case "<=":return left <= right;
            case ">=":return left >= right;
            case "=":
            case "==":return left == right;
        }
        return  false;
    }


    }
