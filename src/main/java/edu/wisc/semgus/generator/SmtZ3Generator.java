/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */

package edu.wisc.semgus.generator;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

import edu.wisc.semgus.fixedpoint.LinearSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class SmtZ3Generator implements edu.wisc.semgus.generator.SmtGenerator {

    @Override
    public boolean checkSat(Vector<Integer> spec, Set<LinearSet> start) {
        Iterator<LinearSet> iterator = start.iterator();
        HashMap<String, String> cfg = new HashMap<String, String>();
        cfg.put("model", "true");
        Context ctx = new Context(cfg);
        while(iterator.hasNext()){
            if(checkInLinearSet_z3(spec,iterator.next(),ctx)) {
                //System.out.print(spec+" "+ls);
                return true;
            }
            iterator.remove();
            //System.out.println(" start next Q");
        }
        return false;
    }

    private boolean checkInLinearSet_z3(Vector<Integer> target, LinearSet ls, Context ctx)  {

        int dim = target.size();

        BoolExpr body;
        IntExpr zero = ctx.mkInt(0);
        BoolExpr assert_nature = ctx.mkTrue();
        List<Vector<Integer>> peroid_list = new ArrayList<>(ls.getPeriod());
        List<IntExpr> bound_vars = new ArrayList<>();

        for(int i = 0; i < ls.getPeriod().size();i++){
            bound_vars.add(ctx.mkIntConst("z"+"_"+i));
            assert_nature = ctx.mkAnd(assert_nature,ctx.mkGe(bound_vars.get(i),zero));
        }

        body = assert_nature;

        for(int j = 0; j < dim; j++){
            ArithExpr body_j = ctx.mkInt(ls.getBase().get(j));
            for(int i = 0; i < ls.getPeriod().size(); i++){
                body_j = ctx.mkAdd(body_j,ctx.mkMul(bound_vars.get(i),ctx.mkInt(peroid_list.get(i).get(j))));
            }
            body =ctx.mkAnd( ctx.mkEq(body_j,ctx.mkInt(target.get(j))),body);
        }

        Solver s = ctx.mkSolver();
        s.add(body);
        return s.check() == Status.SATISFIABLE;
    }

    private static String mkExpr_cmd(String op, String first, String second){
        return "(" +op+" "+first +" "+second+")";
    }

    static String unaryMinus(Integer i){
        if(i < 0)
            return "(- "+(-i);
        return i.toString();
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

    private Set<Vector<Boolean>> getBVSet_recursion(List<LinearSet> left, List<LinearSet> right, String bop, int remain, Set<Vector<Boolean>> result, Vector<Boolean> currentBv) {

        for(LinearSet leftLS:left){
            for(LinearSet rightLS:right){
                result.addAll(getBVSet_recursion_z3(leftLS, rightLS, bop, remain, result, currentBv));
                if(result.size()==Math.pow(2,left.get(0).getBase().size()))
                    return result;
            }
        }
        return result;
    }


    private Set<Vector<Boolean>> getBVSet_recursion_z3(LinearSet left, LinearSet right, String bop, int remain, Set<Vector<Boolean>> result, Vector<Boolean> currentBv) {
        if(remain != 0){
            Vector<Boolean> currentBv_T = new Vector(currentBv);
            Vector<Boolean> currentBv_F = new Vector(currentBv);
            currentBv_T .add(true);
            currentBv_F.add(false);
            result.addAll(getBVSet_recursion_z3(left, right, bop, remain-1, result, currentBv_T));
            result.addAll(getBVSet_recursion_z3(left, right, bop, remain-1, result, currentBv_F));
            return  result;
        }
        if(result.contains(currentBv))
            return result;

        int dim = currentBv.size();
        if(result.size()==Math.pow(2,dim))
            return result;

        float startTime_1 = System.nanoTime();

        HashMap<String, String> cfg = new HashMap<String, String>();
        cfg.put("model", "true");
        Context ctx = new Context(cfg);

        BoolExpr body = ctx.mkTrue();
        // body = true /\ (z = bx1 + x*px1 \/ z = bx2 + x*px2 \/ ...) /\ (z bop by1 + y*py1 \/ ..)
        //                        z \in left                                   z bop z' \in right

        List<IntExpr> boundx_list = new ArrayList<>();
        List<IntExpr> boundy_list = new ArrayList<>();

        BoolExpr assertx = ctx.mkTrue();
        BoolExpr asserty = ctx.mkTrue();
        IntExpr zero = ctx.mkInt(0);

        List<Vector<Integer>> period_list_x = new ArrayList<>(left.getPeriod());
        List<Vector<Integer>> period_list_y = new ArrayList<>(right.getPeriod());

        for(int ix = 0; ix < left.getPeriod().size(); ix++  ){
            boundx_list.add(ctx.mkInt("x_"+ix));
            assertx = ctx.mkAnd(assertx,ctx.mkGe(boundx_list.get(ix),zero));
        }

        for(int iy = 0; iy < right.getPeriod().size(); iy++  ){
            boundy_list.add(ctx.mkInt("y_"+iy));
            asserty = ctx.mkAnd(asserty,ctx.mkGe(boundy_list.get(iy),zero));
        }

        for(int d = 0; d < dim; d++){
            ArithExpr body_left = ctx.mkInt(left.getBase().get(d));
            ArithExpr body_right = ctx.mkInt(right.getBase().get(d));
            for(int i = 0; i < period_list_x.size();i++){
                body_left = ctx.mkAdd(body_left,ctx.mkMul(boundx_list.get(i),ctx.mkInt(period_list_x.get(i).get(d))));
            }
            for(int i = 0; i < period_list_y.size();i++){
                body_right = ctx.mkAdd(body_right,ctx.mkMul(boundy_list.get(i),ctx.mkInt(period_list_y.get(i).get(d))));
            }
            if(currentBv.get(d)) {
                if(bop.equals("<="))
                    body = ctx.mkAnd(body, ctx.mkLe( body_left, body_right));
                if(bop.equals(">="))
                    body = ctx.mkAnd(body, ctx.mkGe( body_left, body_right));
                if(bop.equals("<"))
                    body = ctx.mkAnd(body, ctx.mkLt( body_left, body_right));
                if(bop.equals(">"))
                    body = ctx.mkAnd(body, ctx.mkGt( body_left, body_right));
                if(bop.equals("="))
                    body = ctx.mkAnd(body, ctx.mkEq( body_left, body_right));
            }
            else
            {
                if(bop.equals("<="))
                    body = ctx.mkAnd(body, ctx.mkNot(ctx.mkLe( body_left, body_right)));
                if(bop.equals(">="))
                    body = ctx.mkAnd(body, ctx.mkNot(ctx.mkGe( body_left, body_right)));
                if(bop.equals("<"))
                    body = ctx.mkAnd(body, ctx.mkNot(ctx.mkLt( body_left, body_right)));
                if(bop.equals(">"))
                    body = ctx.mkAnd(body, ctx.mkNot(ctx.mkGt( body_left, body_right)));
                if(bop.equals("="))
                    body = ctx.mkAnd(body, ctx.mkNot(ctx.mkEq( body_left, body_right)));
            }
        }

        body = ctx.mkAnd(body,assertx);
        body = ctx.mkAnd(body,asserty);


        float startTime_2 = System.nanoTime();
        Solver s = ctx.mkSolver();
        s.add(body);
        Boolean sat = s.check() == Status.SATISFIABLE;
        float end = System.nanoTime();
        if((end-startTime_1)/1000>10000) {
            System.out.println(currentBv);
            System.out.println(result);
            System.out.println(body);
            System.out.println("time1: " + (end - startTime_1) / 1000 + "   time2: " + (end - startTime_2) / 1000);
        }

        if(sat) {
            // System.out.println("SAT: "+currentBv+" "+body);
            result.add(currentBv);
        }

        return result;
    }
}
