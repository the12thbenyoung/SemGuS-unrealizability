package edu.wisc.semgus.fixedpoint;

import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.Expression;
import edu.wisc.semgus.utilities.EqType;
import edu.wisc.semgus.utilities.ExpType;

import edu.wisc.semgus.generator.SMTQGenerator;

import java.util.*;

// solve BV query like SL1<SL2

public class BVSolver {
    public static Map<String,Set<Vector<Boolean>>> SolveBV(Integer dim, List<Equation> eqs, Map<String,Set<LinearSet>> assignment, Map<String,Set<Vector<Boolean>>> preBVMap){
//        System.out.println(eqs);
//        System.out.println(assignment);
//        System.out.println(preBVMap);

        Map<String,Set<Vector<Boolean>>> result = new HashMap<>();
        List<String> bvars = getBVars(eqs);

        boolean allfull = true;
        for(String bvar:bvars){
            result.putIfAbsent(bvar,preBVMap.get(bvar));
            if(preBVMap.get(bvar).size() != Math.pow(2,dim))
                allfull = false;
        }
        if(allfull)
            return preBVMap;

        List<Expression> beqs = new ArrayList<>();
        for(Equation eq: eqs){
            if (eq.type != EqType.BOOL)
                continue;
            beqs.add(EqToBEq(eq.right,assignment));
            //Equation valEq = new Equation();
        }
//        System.out.println(beqs);
//        System.out.println("========");

        //System.out.println(eqs);
        Set<String> modfied_var = new HashSet<>();
        for(int i = 0; i < bvars.size(); i++) modfied_var.add(bvars.get(i));
        for(int i = 0; i < bvars.size(); i++) result.put(bvars.get(i), evl(beqs.get(i), preBVMap));
        while(modfied_var.size()!=0){
            Set<String> current_modfied_var = new HashSet<>();
            for(int i = 0; i < bvars.size(); i++){
                if(!id_modified_expr(beqs.get(i),modfied_var)) {
                    continue;
                }
                //System.out.println(dim +" "+result.get(bvars.get(i)).size());
                if(result.get(bvars.get(i)).size() == Math.pow(2,dim))
                    continue;
                Set<Vector<Boolean>> newBVSet = evl(beqs.get(i).right,result);
                assert newBVSet != null;
                if(!newBVSet.equals(result.get(bvars.get(i)))){
                    result.put(bvars.get(i),newBVSet);
                    current_modfied_var.add(bvars.get(i));
                }
            }
            modfied_var = current_modfied_var;
            //System.out.println(modfied_var);

        }
        //System.out.println(result);
        return  result;
    }

    private static Set<Vector<Boolean>> evl(Expression<Set<Vector<Boolean>>> expr, Map<String, Set<Vector<Boolean>>> result) {
        switch (expr.type){
            case CONST:
                return expr.constant;
            case VAR:
                return result.get(expr.var);
            case OPLUS:
                Set<Vector<Boolean>> union = new HashSet<>();
                union.addAll(evl(expr.left,result));
                union.addAll(evl(expr.right,result));

                return union;
            case BOOL:
                return evl_bop(evl(expr.left,result),evl(expr.right,result),expr.bop);
            case NOT:
                return evl_bop(evl(expr.left,result),new HashSet<>(),"not");
            default:
                System.out.println("ERROR: wrong type evl "+expr);
        }

        return  null;
    }

    private static Set<Vector<Boolean>> evl_bop(Set<Vector<Boolean>>  evlL, Set<Vector<Boolean>>  evlR, String bop) {
        Set<Vector<Boolean>> result = new HashSet<>();
        for(Vector<Boolean> bvL:evlL){
            for(Vector<Boolean> bvR:evlR){
                Vector<Boolean> newBV = new Vector<>();
                switch (bop){
                    case "and":
                        for(int i = 0; i < bvL.size();i++){
                            newBV.add(bvL.get(i)&&bvR.get(i));
                        }
                        break;
                    case "or":
                        for(int i = 0; i < bvL.size();i++){
                            newBV.add(bvL.get(i)||bvR.get(i));
                        }
                }
                result.add(newBV);
            }
            if(evlR.size() == 0){
                Vector<Boolean> newBV = new Vector<>();
                for(int i = 0; i < bvL.size();i++){
                    newBV.add(!bvL.get(i));
                }
                result.add(newBV);
            }
        }
        return result;
    }

    private static boolean id_modified_expr(Expression expr, Set<String> modified_var) {
        switch (expr.type){
            case CONST:
                return false;
            case VAR:
                if(modified_var.contains(expr.var))
                    return true;
                return false;
            case BOOL:
                return id_modified_expr(expr.left,modified_var) || id_modified_expr(expr.right,modified_var);
            case NOT:
                return id_modified_expr(expr.left,modified_var);
            default:
                System.out.println("ERROR: wrong type id_modified_expr");
                return false;
        }
    }

    private static Expression EqToBEq(Expression eq, Map<String, Set<LinearSet>> assignment) {
        Expression result = new Expression();
        switch (eq.type){
            case VAR:
                return eq;
            case OTIMES:
                result.type = ExpType.OTIMES;
                result.left = EqToBEq(eq.left,assignment);
                result.right = EqToBEq(eq.right,assignment);
                return result;
            case OPLUS:
                result.type = ExpType.OPLUS;
                result.left = EqToBEq(eq.left,assignment);
                result.right = EqToBEq(eq.right,assignment);
                return result;
            case BOOL:
                if(eq.bop.equals("and") || eq.bop.equals("or"))
                    return eq;
                result.type = ExpType.CONST;
                result.constant = SMTQGenerator.getBVSet(ExpressionApplication.ExpressionEval_SemilinearSet(eq.left,assignment),ExpressionApplication.ExpressionEval_SemilinearSet(eq.right,assignment),eq.bop);
                return result;
            case NOT:
                return eq;
            default:
                System.out.println("ERROR: wrong type in EqToBEq");
                return null;
        }
    }

    private  static List<String> getBVars (List<Equation> eqs){
        List<String> result = new ArrayList<>();
        for(Equation eq:eqs){
            if(eq.type == EqType.BOOL){
                result.add(eq.left);
            }
        }
        return result;
    }
}
