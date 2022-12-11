package edu.wisc.semgus.fixedpoint;
import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.ExpType;
import edu.wisc.semgus.utilities.Expression;

import java.util.*;

public  class ExpressionApplication {


    public static Set<LinearSet> ExpressionEval_SemilinearSet(Expression<Set<LinearSet>> exp, Map<String,Set<LinearSet>> assignment){
        Set<LinearSet> result;
        switch (exp.type){
            case CONST:
                return  exp.constant;
            case VAR:
                return assignment.get(exp.var);

            case OTIMES:
                result = SemilinearFactory.dot(ExpressionEval_SemilinearSet(exp.left,assignment), ExpressionEval_SemilinearSet(exp.right,assignment));
                return result;
            case OPLUS:
                result = SemilinearFactory.union(ExpressionEval_SemilinearSet(exp.left,assignment), ExpressionEval_SemilinearSet(exp.right,assignment));
                return result;
            default:
                System.out.println("ERROR: wrong type while ExpressionApplication_SemilinearSet "+exp.toString());
                return null;
        }
    }

    public static Expression ExpressionApplication_SemilinearSet(Expression exp,  Map<String,Set<LinearSet>> assignment){
        Expression result = new Expression();
        switch (exp.type){
            case CONST:
                return  exp;
            case VAR:
                if(assignment.keySet().contains(exp.var)){

                    result.type = ExpType.CONST;
                    result.constant = assignment.get(exp.var);
                    return result;
                }
                return exp;
            case OTIMES:
                result.type = ExpType.OTIMES;
                result.left = ExpressionApplication_SemilinearSet(exp.left,assignment);
                result.right = ExpressionApplication_SemilinearSet(exp.right,assignment);
                return result;
            case OPLUS:
                result.type = ExpType.OPLUS;
                result.left = ExpressionApplication_SemilinearSet(exp.left,assignment);
                result.right = ExpressionApplication_SemilinearSet(exp.right,assignment);
                return result;
            case ITE:
                result.type = ExpType.ITE;
                result.left = ExpressionApplication_SemilinearSet(exp.left,assignment);
                result.right = ExpressionApplication_SemilinearSet(exp.right,assignment);
                result.condition = ExpressionApplication_SemilinearSet(exp.condition,assignment);
                return result;
            case BOOL:
                result.type = ExpType.BOOL;
                result.bop = exp.bop;
                result.left = ExpressionApplication_SemilinearSet(exp.left,assignment);
                result.right = ExpressionApplication_SemilinearSet(exp.right,assignment);
                return result;
            case NOT:
                result.type = ExpType.NOT;
                result.bop = exp.bop;
                result.left = ExpressionApplication_SemilinearSet(exp.left,assignment);
                return result;
        }
        return null;
    }

    public static List<Equation> EquationEval_LinearSet(List<Equation> Eqs, Map<String,Vector<Integer>> map){
        List<Equation> result = new ArrayList<Equation>();
        for(Equation currentEq : Eqs){
            Equation toAdd = new Equation(currentEq.left, ExpressionApplication_LinearSet(currentEq.right,map));
            toAdd.type = currentEq.type;
            result.add(toAdd);
        }
        return  result;
    }


    public static Expression<Set<LinearSet>> ExpressionApplication_LinearSet(Expression<Integer> exp, Map<String,Vector<Integer>> map){
        int dim = map.values().iterator().next().size();
        Expression<Set<LinearSet>> result = new Expression();
        if(exp.type == ExpType.CONST){
            Vector<Integer> constVec = new Vector<>();
            for(int i = 0; i < dim; i++){
                constVec.add(exp.constant);
            }
            result.type = ExpType.CONST;
            result.constant = new HashSet<>();
            result.constant.add(new LinearSet(constVec));
            return result;
        }
        if(exp.type == ExpType.VAR){
            if(exp.var.substring(0,1).equals("-")){
                String var = exp.var.substring(1,exp.var.length());
                if(map.containsKey(var)){
                    result.type = ExpType.CONST;
                    result.constant = new HashSet<>();
                    Vector<Integer> toAdd = new Vector<>();
                    for(int i = 0; i < map.get(var).size(); i++){
                        toAdd.add(-map.get(var).get(i));
                    }
                    result.constant.add(new LinearSet(toAdd));
                    return  result;
                }
            }
            if(map.containsKey(exp.var)){
                result.type = ExpType.CONST;
                result.constant = new HashSet<>();
                result.constant.add(new LinearSet(map.get(exp.var)));
                return  result;
            }
            result.type = ExpType.VAR;
            result.var = exp.var;
            return result;
        }

        if(exp.type == ExpType.OPLUS || exp.type == ExpType.OTIMES){
            result.type = exp.type;
            result.left = ExpressionApplication_LinearSet(exp.left,map);
            result.right = ExpressionApplication_LinearSet(exp.right,map);
            return  result;
        }

        if(exp.type == ExpType.ITE){
            result.type = ExpType.ITE;
            result.condition = ExpressionApplication_LinearSet(exp.condition,map);
            result.left = ExpressionApplication_LinearSet(exp.left,map);
            result.right = ExpressionApplication_LinearSet(exp.right,map);
            return  result;
        }

        if(exp.type == ExpType.BOOL){
            result.type = ExpType.BOOL;
            result.bop = exp.bop;
            result.left = ExpressionApplication_LinearSet(exp.left,map);
            result.right = ExpressionApplication_LinearSet(exp.right,map);
            return  result;
        }
        if(exp.type == ExpType.NOT){
            result.type = ExpType.NOT;
            result.bop = exp.bop;
            result.left = ExpressionApplication_LinearSet(exp.left,map);
            return  result;
        }
        System.out.println("Error: invalid type: "+result.type.toString() );
        return  null;
    }

    private static int count = 0;


    public static Expression ExpressionApplication_BVSet(Expression exp, Map<String, Set<Vector<Boolean>>> currentBV) {
        Expression result = new Expression();
        switch (exp.type){
            case CONST:
                return  exp;
            case VAR:
                if(currentBV.keySet().contains(exp.var)){

                    result.type = ExpType.CONST;
                    Set<Vector<Boolean>> newBVSet = new HashSet<>();
                    for(Vector<Boolean> bv:  currentBV.get(exp.var)){
                        Vector<Boolean> newBv = new Vector<>();
                        for(Boolean b: bv)
                            newBv.add(b);
                        newBVSet.add(newBv);
                    }
                    result.constant = newBVSet;
                    return result;
                }
                return exp;
            case OTIMES:
                result.type = ExpType.OTIMES;
                result.left = ExpressionApplication_BVSet(exp.left,currentBV);
                result.right = ExpressionApplication_BVSet(exp.right,currentBV);
                return result;
            case OPLUS:
                result.type = ExpType.OPLUS;
                result.left = ExpressionApplication_BVSet(exp.left,currentBV);
                result.right = ExpressionApplication_BVSet(exp.right,currentBV);
                return result;
            case ITE:
                result.type = ExpType.ITE;
                result.left = ExpressionApplication_BVSet(exp.left,currentBV);
                result.right = ExpressionApplication_BVSet(exp.right,currentBV);
                result.condition = ExpressionApplication_BVSet(exp.condition,currentBV);
                return result;
            case BOOL:
                result.type = ExpType.BOOL;
                result.bop = exp.bop;
                result.left = ExpressionApplication_BVSet(exp.left,currentBV);
                result.right = ExpressionApplication_BVSet(exp.right,currentBV);
                return result;
            case NOT:
                result.type = ExpType.NOT;
                result.bop = exp.bop;
                result.left = ExpressionApplication_BVSet(exp.left,currentBV);
                return result;
        }
        return null;
    }

}
