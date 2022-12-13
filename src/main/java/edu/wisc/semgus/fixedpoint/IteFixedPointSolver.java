/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.fixedpoint;

import edu.wisc.semgus.utilities.Expression;
import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.ExpType;
import edu.wisc.semgus.utilities.EqType;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IteFixedPointSolver {
    private  static int dim = 0;
    public static int totalStage = 0;
    public static float bvSize = 0;
    public static int bvCount = 0;
    public static Boolean opt = true;
    public static Map<String,Set<LinearSet>> SolveIteFixedPoint(List<Equation> termEqs, Map<String, Vector<Integer>> map){
        Map<String,Set<LinearSet>> finalSolution = new HashMap<>();
        dim = map.values().iterator().next().size();
        List<Equation> oriValEqs = ExpressionApplication.EquationEval_LinearSet(termEqs,map);
        DAG dag = new DAG(oriValEqs);
        Set<String> currentEq = new HashSet<>();
        if (opt)
            currentEq = dag.popRoot();
        else{
            for(Equation eq:termEqs)
                currentEq.add(eq.left);
        }

        while(currentEq!=null) {

            // System.out.println("current eq: "+currentEq);
            List<Equation> valEqs = new ArrayList<>();
            int stage = 0;
            for (Equation oriEq : oriValEqs) {
                if (currentEq.contains(oriEq.left))
                    valEqs.add(oriEq);
            }

            // # of ite in arithmetic non-terminal
            List<String> boolNames = IntStream.range(0, valEqs.size())
                                        .filter(i -> valEqs.get(i).type == EqType.BOOL)
                                        .mapToObj(i -> valEqs.get(i).left).collect(Collectors.toList());

            Map<Integer, Map<String, Set<Vector<Boolean>>>> bvStore = new HashMap<>();
            Map<String, Set<Vector<Boolean>>> initBV = new HashMap<>();
            for (String boolName : boolNames) {
                initBV.put(boolName, new HashSet<>());
            }
            bvStore.put(0, initBV);

            // start solving fixed point
            while (true) {
                // System.out.println(bvStore);
                // substitute ite in eqs by previous solution
                //System.out.println("Stage: "+stage);
                List<Equation> valEqs_BV_substituted = new ArrayList<>();
                for(Equation eq: valEqs) {
                    Equation newEq = new Equation(eq.left,ExpressionApplication.ExpressionApplication_BVSet(eq.right, bvStore.get(stage)));
                    newEq.type = eq.type;
//                    System.out.println(eq);
//                    System.out.println(newEq + "\n========");
                    valEqs_BV_substituted.add(newEq);
                }
                List<Equation> valEqsNoIte = expandIte(valEqs_BV_substituted);
//                System.out.println(valEqs_BV_substituted);
//                System.out.println(valEqsNoIte + "\n==========");
                //System.out.println("\t \t valEqsNoIte got");
                // map from eq to var set appearing in the rhs
                Map<String, Set<String>> rhs_var_set = new HashMap<>();
                for (Equation eq : valEqsNoIte) {
                    if (eq.type == EqType.INT)
                        rhs_var_set.put(eq.left, find_expr_vars(eq.right));
                }
                // solving linear eqs by newton method

                //System.out.println("Newton Start");
                Map<String, Set<LinearSet>> currentSolution = Newton.SolveSlEq(valEqsNoIte, (map.values().iterator().next()).size(), rhs_var_set);

                // get the new bv map with new solution
                //System.out.println("BVSolver Start");
                Map<String, Set<Vector<Boolean>>> currentBV = BVSolver.SolveBV(dim, valEqs, currentSolution, bvStore.get(stage));
                // System.out.println(currentBV);

                if (checkBVFixedPoint(currentBV, bvStore.get(stage))) {
                    // fixed point reached
                    for (Equation oriEq : oriValEqs) {
                        oriEq.right = ExpressionApplication.ExpressionApplication_SemilinearSet(oriEq.right, currentSolution);
                        oriEq.right = ExpressionApplication.ExpressionApplication_BVSet(oriEq.right, currentBV);
                    }

                    for(String key:currentBV.keySet()){
                        bvSize = bvSize*bvCount+currentBV.get(key).size();
                        bvCount++;
                        bvSize = bvSize/bvCount;
                    }

                    for(String key:currentSolution.keySet()){
                        // System.out.println(key+" : "+currentSolution.get(key).size());
                        //System.out.println(currentSolution.get(key));
                    }
                    //System.out.println(currentSolution);
                    finalSolution = currentSolution;
                    break;
                }
                stage++;
                totalStage++;
                bvStore.put(stage, currentBV);
            }
            if(opt)
                currentEq = dag.popRoot();
            else break;
        }
        return finalSolution;
    }

    private static List<Equation> expandIte(List<Equation> oriEqs){
        Set<Pair<String,Vector<Boolean>>> newNTs = new HashSet<>();
        List<Equation> result = new ArrayList<>();
        for(Equation eq: oriEqs){
            if(eq.type == EqType.BOOL)
                continue;
            Pair<Set<Pair<String,Vector<Boolean>>>,Expression> expandResult = expandIte(eq.right);
            Equation newEq = new Equation(eq.left,expandResult.second);
            newEq.type = eq.type;
            result.add(newEq);
            newNTs.addAll(expandResult.first);
        }
        while(newNTs.size() != 0) {
            Set<Pair<String,Vector<Boolean>>> visited = new HashSet<>(newNTs);
            Set<Pair<String,Vector<Boolean>>> toVisit = new HashSet<>();
            for (Pair<String, Vector<Boolean>> newNT : newNTs) {
                if(isTrue(newNT.second))
                    continue;
                Pair<Set<Pair<String, Vector<Boolean>>>, Expression> projresult;
                projresult = projection_Expression(lookupNT(newNT.first, oriEqs).right, newNT.second);
                Equation newEq = new Equation(newNT.first + toBitString(newNT.second), projresult.second);
                for (Pair<String, Vector<Boolean>> candidate : projresult.first) {
                    if (!visited.contains(candidate))
                        toVisit.add(candidate);
                }
                newEq.type = lookupNT(newNT.first,oriEqs).type;
                result.add(newEq);
            }
            newNTs = toVisit;
        }
        return result;
    }

    private static Equation lookupNT(String nt, List<Equation>eqs){
        for(Equation eq:eqs){
            if(eq.left.equals(nt))
                return eq;
        }
        System.out.println("ERROR");
        System.out.println(nt);
        System.out.println(eqs);
        return null;
    }


    private static Pair<Set<Pair<String,Vector<Boolean>>>,Expression> projection_Expression(Expression exp, Vector<Boolean> bv){
        Expression resultExpr = new Expression();
        Pair<Set<Pair<String,Vector<Boolean>>>,Expression> tmpRight;
        Pair<Set<Pair<String,Vector<Boolean>>>,Expression> tmpLeft;
        Set<Pair<String,Vector<Boolean>>> resultSet = new HashSet<>();
        if(isFalse(bv)){
            resultExpr.type = ExpType.CONST;
            resultExpr.constant = new HashSet<>();
            Vector<Integer> zero = new Vector<>();
            for(Boolean b:bv){
                zero.add(0);
            }
            ((Set)resultExpr.constant).add(new LinearSet(zero,new HashSet<>()));
            return (Pair<Set<Pair<String, Vector<Boolean>>>, Expression>) new Pair(resultSet,resultExpr);
        }
        switch (exp.type){
            case CONST:
                resultExpr.type = ExpType.CONST;
                resultExpr.constant = projection_SL((Set<LinearSet> )exp.constant,bv);
                break;
            case VAR:
                if(isFalse(bv)){
                    resultExpr.type = ExpType.CONST;
                    Vector<Integer> zero = new Vector<>();
                    for(Boolean b:bv){
                        zero.add(0);
                    }
                    resultExpr.constant = zero;
                    break;
                }
                resultExpr.type = ExpType.VAR;
                resultExpr.var = exp.var+toBitString(bv);
                resultSet.add(new Pair<>(exp.var,bv));
                break;
            case OTIMES:
            case OPLUS:
                resultExpr.type = exp.type;
                tmpLeft = projection_Expression(exp.left,bv);
                tmpRight = projection_Expression(exp.right,bv);
                resultExpr.left = tmpLeft.second;
                resultExpr.right = tmpRight.second;
                resultSet.addAll(tmpLeft.first);
                resultSet.addAll(tmpRight.first);
                break;
            case ITE:
                for(Vector<Boolean> newBv: bvand(bv,(Set<Vector<Boolean>>)exp.condition.constant)) {
                    if(exp.left.type == ExpType.VAR)
                        resultSet.add(new Pair(exp.left.var, newBv));
                }
                for(Vector<Boolean> newBv: bvand(bv,flip((Set<Vector<Boolean>>)exp.condition.constant))) {
                    if(exp.right.type == ExpType.VAR)
                        resultSet.add(new Pair(exp.right.var, newBv));
                }
                if(exp.left.type == ExpType.VAR && exp.right.type == ExpType.VAR)
                    resultExpr = constructSum(exp.left.var,exp.right.var,bvand(bv,(Set<Vector<Boolean>>)exp.condition.constant), bv);
                if(exp.left.type == ExpType.CONST && exp.right.type == ExpType.VAR)
                    resultExpr = constructSum((Set<LinearSet>)exp.left.constant,exp.right.var,bvand(bv,(Set<Vector<Boolean>>)exp.condition.constant),bv);
                if(exp.left.type == ExpType.VAR && exp.right.type == ExpType.CONST)
                    resultExpr = constructSum(exp.left.var,(Set<LinearSet>)exp.right.constant,bvand(bv,(Set<Vector<Boolean>>)exp.condition.constant),bv);
                if(exp.left.type == ExpType.CONST && exp.right.type == ExpType.CONST)
                    resultExpr = constructSum((Set<LinearSet>)exp.left.constant,(Set<LinearSet>)exp.right.constant,bvand(bv,(Set<Vector<Boolean>>)exp.condition.constant),bv);
            default:
                break;

        }
        return (Pair<Set<Pair<String, Vector<Boolean>>>, Expression>) new Pair(resultSet,resultExpr);
    }

    private static Set<Vector<Boolean>>  bvand(Vector<Boolean> bv, Set<Vector<Boolean>> BVSet){
        Set<Vector<Boolean>> result = new HashSet<>();
        for(Vector<Boolean> bvthat: BVSet){
            Vector<Boolean> newBv = new Vector<>();
            for(int i =0; i < bv.size(); i++){
                newBv.add(bv.get(i)&&bvthat.get(i));
            }
            result.add(newBv);
        }
        return result;
    }

    private static Vector<Boolean>  bvand(Vector<Boolean> bv, Vector<Boolean> bvthat){
            Vector<Boolean> newBv = new Vector<>();
            for(int i =0; i < bv.size(); i++){
                newBv.add(bv.get(i)&&bvthat.get(i));
            }
        return newBv;
    }



    private static Pair<Set<Pair<String,Vector<Boolean>>>,Expression> expandIte(Expression exp){
        Expression resultExpr = new Expression();
        Set<Pair<String,Vector<Boolean>>> resultSet = new HashSet<>();
        Pair<Set<Pair<String,Vector<Boolean>>>,Expression> tmpLeft;
        Pair<Set<Pair<String,Vector<Boolean>>>,Expression> tmpRight;
        switch (exp.type){
            case CONST:
            case VAR:
                resultExpr = exp;
                break;
            case OTIMES:
            case OPLUS:
                resultExpr.type = exp.type;
                tmpLeft = expandIte(exp.left);
                tmpRight = expandIte(exp.right);
                resultExpr.left = tmpLeft.second;
                resultExpr.right = tmpRight.second;
                resultSet.addAll(tmpLeft.first);
                resultSet.addAll(tmpRight.first);
                break;
            case ITE:
                if(exp.left.type == ExpType.VAR)
                    for(Vector<Boolean> bv: (Set<Vector<Boolean>>)exp.condition.constant)
                        resultSet.add(new Pair(exp.left.var,bv));

                if(exp.right.type == ExpType.VAR)
                    for(Vector<Boolean> bv: flip((Set<Vector<Boolean>>)exp.condition.constant))
                        resultSet.add(new Pair(exp.right.var,bv));
                Vector<Boolean> trueV = new Vector<>();
                for(int i = 0; i < dim; i++)
                    trueV.add(true);
                if(exp.left.type == ExpType.VAR && exp.right.type == ExpType.VAR)
                    resultExpr = constructSum(exp.left.var,exp.right.var,(Set<Vector<Boolean>>)exp.condition.constant,trueV);
                if(exp.left.type == ExpType.CONST && exp.right.type == ExpType.VAR)
                    resultExpr = constructSum((Set<LinearSet>)exp.left.constant,exp.right.var,(Set<Vector<Boolean>>)exp.condition.constant,trueV);
                if(exp.left.type == ExpType.VAR && exp.right.type == ExpType.CONST)
                    resultExpr = constructSum(exp.left.var,(Set<LinearSet>)exp.right.constant,(Set<Vector<Boolean>>)exp.condition.constant,trueV);
                if(exp.left.type == ExpType.CONST && exp.right.type == ExpType.CONST)
                    resultExpr = constructSum((Set<LinearSet>)exp.left.constant,(Set<LinearSet>)exp.right.constant,(Set<Vector<Boolean>>)exp.condition.constant,trueV);
            default:
                break;
        }
        return (Pair<Set<Pair<String, Vector<Boolean>>>, Expression>) new Pair(resultSet,resultExpr);
    }

    private static Expression constructSum(String left, String right, Set<Vector<Boolean>> bvSet, Vector<Boolean> mask) {
        Expression result = new Expression();
        if(bvSet.size() == 0)
        {
            result.type = ExpType.CONST;
            result.constant = new HashSet<>();
            return result;
        }
        if(bvSet.size() == 1){
            result.type = ExpType.OTIMES;
            result.left = new Expression();
            result.left.type = ExpType.VAR;
            result.left.var = left + toBitString(bvand(mask,bvSet.iterator().next()));
            result.right = new Expression();
            result.right.type = ExpType.VAR;
            result.right.var = right + toBitString(bvand(mask,flip(bvSet.iterator().next())));
            return  result;
        }

        result.type = ExpType.OPLUS;
        Iterator<Vector<Boolean>> iterator = bvSet.iterator();
        Vector<Boolean> bv = iterator.next();
        iterator.remove();
        result.right = constructSum(left, right, bvSet,mask);

        Expression leftExpr = new Expression();
        leftExpr.type = ExpType.OTIMES;
        leftExpr.left = new Expression();
        leftExpr.left.type = ExpType.VAR;
        leftExpr.left.var = left + toBitString(bvand(mask,bv));
        leftExpr.right = new Expression();
        leftExpr.right.type = ExpType.VAR;
        leftExpr.right.var = right + toBitString(bvand(mask,flip(bv)));
        result.left = leftExpr;
        return result;
    }

    private static Expression constructSum(Set<LinearSet> left, String right, Set<Vector<Boolean>> bvSet, Vector<Boolean> mask) {
        Expression result = new Expression();
        if(bvSet.size() == 0)
        {
            result.type = ExpType.CONST;
            result.constant = new HashSet<>();
            return result;
        }
        if(bvSet.size() == 1){
            result.type = ExpType.OTIMES;
            result.left = new Expression();
            result.left.type = ExpType.CONST;
            result.left.constant = projection_SL(left,bvand(mask,bvSet.iterator().next()));
            result.right = new Expression();
            result.right.type = ExpType.VAR;
            result.right.var = right + toBitString(bvand(mask,flip(bvSet.iterator().next())));
            return  result;
        }

        result.type = ExpType.OPLUS;
        Iterator<Vector<Boolean>> iterator = bvSet.iterator();
        Vector<Boolean> bv = iterator.next();
        iterator.remove();
        result.right = constructSum(left, right, bvSet,mask);

        Expression leftExpr = new Expression();
        leftExpr.type = ExpType.OTIMES;
        leftExpr.left = new Expression();
        leftExpr.left.type = ExpType.CONST;
        leftExpr.left.constant = projection_SL(left,bvand(mask,bv));
        leftExpr.right = new Expression();
        leftExpr.right.type = ExpType.VAR;
        leftExpr.right.var = right + toBitString(bvand(mask,flip(bv)));
        result.left = leftExpr;
        return result;
    }

    private static Expression constructSum(String left, Set<LinearSet> right, Set<Vector<Boolean>> bvSet, Vector<Boolean> mask) {
        Expression result = new Expression();
        if(bvSet.size() == 0)
        {
            result.type = ExpType.CONST;
            result.constant = new HashSet<>();
            return result;
        }
        if(bvSet.size() == 1){
            result.type = ExpType.OTIMES;
            result.left = new Expression();
            result.left.type = ExpType.VAR;
            result.left.var = left + toBitString(bvand(mask,bvSet.iterator().next()));
            result.right = new Expression();
            result.right.type = ExpType.CONST;
            result.right.constant = projection_SL(right,bvand(mask,flip(bvSet.iterator().next())));
            return  result;
        }

        result.type = ExpType.OPLUS;
        Iterator<Vector<Boolean>> iterator = bvSet.iterator();
        Vector<Boolean> bv = iterator.next();
        iterator.remove();
        result.right = constructSum(left, right, bvSet,mask);

        Expression leftExpr = new Expression();
        leftExpr.type = ExpType.OTIMES;
        leftExpr.left = new Expression();
        leftExpr.left.type = ExpType.VAR;
        leftExpr.left.var = left + toBitString(bvand(mask,bv));
        leftExpr.right = new Expression();
        leftExpr.right.type = ExpType.CONST;
        leftExpr.right.constant = projection_SL(right,bvand(mask,flip(bv)));
        result.left = leftExpr;
        return result;
    }

    private static Expression constructSum(Set<LinearSet> left, Set<LinearSet> right, Set<Vector<Boolean>> bvSet, Vector<Boolean> mask) {
        Expression result = new Expression();
        if(bvSet.size() == 0)
        {
            result.type = ExpType.CONST;
            result.constant = new HashSet<>();
            return result;
        }
        if(bvSet.size() == 1){
            result.type = ExpType.CONST;;
            result.constant = SemilinearFactory.dot(projection_SL(left,bvand(mask,bvSet.iterator().next())),projection_SL(right,bvand(mask,flip(bvSet.iterator().next()))));
            return  result;
        }

        result.type = ExpType.OPLUS;
        Iterator<Vector<Boolean>> iterator = bvSet.iterator();
        Vector<Boolean> bv = iterator.next();
        iterator.remove();
        result.right = constructSum(left,right, bvSet,mask);

        Expression leftExpr = new Expression();
        leftExpr.type = ExpType.CONST;
        leftExpr.constant =SemilinearFactory.dot(projection_SL(left,bvand(mask,bv)),projection_SL(right,bvand(mask,flip(bv))));
        result.left = leftExpr;
        return result;
    }

    private static boolean isFalse(Vector<Boolean> v){
        for(Boolean b:v){
            if(b)
                return false;
        }
        return true;
    }

    private static boolean isTrue(Vector<Boolean> v){
        for(Boolean b:v){
            if(!b)
                return false;
        }
        return true;
    }

    private static String toBitString(Vector<Boolean> bv){
        String result = "";
        boolean flag = true;
        for(Boolean b:bv){
            flag = flag && b;
            if (b)
                result+="1";
            else
                result+="0";
        }
        if (flag)
            return "";
        return "_"+result.toString();
    }

    private static Set<String> find_expr_vars(Expression expr) {
        Set<String> result = new HashSet<>();
        switch (expr.type){
            case CONST:
                return result;
            case VAR:
                result.add(expr.var);
                return result;
            case OTIMES:
            case OPLUS:
                result.addAll(find_expr_vars(expr.left));
                result.addAll(find_expr_vars(expr.right));
            default:
                break;
        }
        return result;

    }





    private static boolean checkBVFixedPoint(Map<String, Set<Vector<Boolean>>> currentBV, Map<String, Set<Vector<Boolean>>> previousBV) {
        for(String var:currentBV.keySet()){
            if(!currentBV.get(var).equals(previousBV.get(var)))
                return  false;
        }
        return true;
    }


    private static Set<Vector<Boolean>> flip(Set<Vector<Boolean>> bvSet) {
        Set<Vector<Boolean>> result = new HashSet<>();
        for(Vector<Boolean> bv: bvSet){
            result.add(flip(bv));
        }
        return result;
    }

    private static Vector<Boolean> flip(Vector<Boolean> bv) {
            Vector<Boolean> newBv = new Vector<>();
        for (Boolean aBoolean : bv) {
            newBv.add(!aBoolean);
        }
        return newBv;
    }



    private static Set<LinearSet> projection_SL(Set<LinearSet> sl,Vector<Boolean> bv){
        Set<LinearSet> result = new HashSet<>();
        for(LinearSet ls: sl){
            Vector<Integer> newBase = new Vector<>();
            Set<Vector<Integer>> newPeriod = new HashSet<>();
            for(int i = 0; i < bv.size(); i++){
                if (bv.get(i))
                    newBase.add(ls.getBase().get(i));
                else
                    newBase.add(0);
            }
            for(Vector<Integer> pv: ls.getPeriod()){
                Vector<Integer> newPv = new Vector<>();
                for(int i = 0; i < bv.size(); i++){
                    if (bv.get(i))
                        newPv.add(pv.get(i));
                    else
                        newPv.add(0);
                }
                newPeriod.add(newPv);
            }
            result.add(new LinearSet(newBase,newPeriod));
        }
        return  result;
    }

}
