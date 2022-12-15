/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.utilities;

import edu.wisc.semgus.parser.ProdSemantics;
import edu.wisc.semgus.parser.ProdSemanticsGenerator;

public class Expression<E> {
    public ExpType type;
    public String var;
    public Expression left;
    public Expression right;
    public Expression condition;
    public E constant;
    public String bop;

    public Expression() {
    }

    // Var constructor
    public Expression(String s) {
        this.type = ExpType.VAR;
        this.var = s;
    }

    // Expression with children
    public Expression(ExpType type, Expression left, Expression right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    // constructor with just type
    public Expression (ExpType type) {
        this.type = type;
    }

    // oplus self with right 
    public Expression oplus(Expression right) {
        return new Expression(ExpType.OPLUS, this, right);
    }
    
    // Use semantic equivalence testing (in semGenerator) to create expression from production
    public static Expression<Integer> inferIntExpression(String op, String[] occurrences, ProdSemantics sem, ProdSemanticsGenerator semGenerator){
        // to be returned
        Expression<Integer> exp = new Expression<Integer>();

        String inputTest = semGenerator.isInput(sem);
        int constTest = semGenerator.isConst(sem);
        if (!inputTest.equals("")) {
            // input variable (e.g. x,y,z)
            exp.var = inputTest;
            exp.type = ExpType.VAR;
        } else if (constTest != Integer.MIN_VALUE) {
            // constant integer
            exp.constant = constTest;
            exp.type = ExpType.CONST;
        } else if (semGenerator.isPlus(sem)) {
            exp.type = ExpType.OTIMES;
            exp.left = new Expression<Integer>(occurrences[0]);
            exp.right = new Expression<Integer>(occurrences[1]);
        } else if (semGenerator.isIte(sem)) {
            exp.type = ExpType.ITE;
            exp.condition = new Expression<Boolean>(occurrences[0]);
            exp.left = new Expression<Integer>(occurrences[1]);
            exp.right = new Expression<Integer>(occurrences[2]);
        } else {
            System.out.println("Unable to match semantics of integer-valued production " + op + " with CLIA");
            throw new RuntimeException("Unable to match semantics of integer-valued production " + op + " with CLIA");
        }

        return exp;
    }
    
    public static Expression<Boolean> inferBoolExpression(String op, String[] occurrences, ProdSemantics sem, ProdSemanticsGenerator semGenerator){
        // to be returned
        Expression<Boolean> exp = new Expression<Boolean>();
        if (semGenerator.isTrue(sem)) {
            exp.type = ExpType.CONST;
            exp.constant = true;
        } else if (semGenerator.isFalse(sem)) {
            exp.type = ExpType.CONST;
            exp.constant = false;
        } else if (semGenerator.isNot(sem)) {
            exp.bop = "not";
            exp.type = ExpType.NOT;
            exp.left = new Expression<Boolean>(occurrences[0]);
        } else if (semGenerator.isAnd(sem)) {
            exp.type = ExpType.BOOL;
            exp.bop = "and";
            exp.left = new Expression<Boolean>(occurrences[0]);
            exp.right = new Expression<Boolean>(occurrences[1]);
        } else if (semGenerator.isOr(sem)) {
            exp.type = ExpType.BOOL;
            exp.bop = "or";
            exp.left = new Expression<Boolean>(occurrences[0]);
            exp.right = new Expression<Boolean>(occurrences[1]);
        } else if (semGenerator.isGeq(sem)) {
            exp.type = ExpType.BOOL;
            exp.bop = ">=";
            exp.left = new Expression<Integer>(occurrences[0]);
            exp.right = new Expression<Integer>(occurrences[1]);
        } else if (semGenerator.isGt(sem)) {
            exp.type = ExpType.BOOL;
            exp.bop = ">";
            exp.left = new Expression<Integer>(occurrences[0]);
            exp.right = new Expression<Integer>(occurrences[1]);
        } else if (semGenerator.isLeq(sem)) {
            exp.type = ExpType.BOOL;
            exp.bop = "<=";
            exp.left = new Expression<Integer>(occurrences[0]);
            exp.right = new Expression<Integer>(occurrences[1]);
        } else if (semGenerator.isLt(sem)) {
            exp.type = ExpType.BOOL;
            exp.bop = "<";
            exp.left = new Expression<Integer>(occurrences[0]);
            exp.right = new Expression<Integer>(occurrences[1]);
        }
        else {
            System.out.println("Unable to match semantics of boolean-valued production " + op + " with CLIA");
            throw new RuntimeException("Unable to match semantics of boolean-valued production " + op + " with CLIA");
        }

        return exp;
    }

    @Override
    public String toString(){
        switch (this.type) {
            case OTIMES:
                return left.toString()+"(*)"+right.toString();
            case OPLUS:
                return left.toString() + "(+)" + right.toString();
            case CONST :
                return this.constant.toString();
            case VAR:
                return this.var.toString();
            case ITE:
                return "(ITE ("+ this.condition.toString()+") " +" ("+this.left.toString()+") "+ " ("+this.right.toString()+") "+")";
            case BOOL:
                return this.left.toString()+" "+this.bop +" "+this.right.toString();
            case NOT:
                return this.bop +" "+this.left.toString();
            default:
                System.out.println("ERROR: invalid expression type: " + this.type.toString());
                return null;
        }
    }
}