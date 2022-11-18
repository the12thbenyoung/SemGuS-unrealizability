/* Modified from original Nay solver
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus;

import javax.management.RuntimeErrorException;

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
    
    // Infers expression from (operator, occurrences) pair read from grammar for int type nonterminal
    public static Expression<Integer> inferIntExpression(String operator, String[] occurrences){
        Expression<Integer> exp = new Expression<Integer>();
        // assume all operators begin with $
        if (operator.charAt(0) != '$') {
            throw new RuntimeException("All grammar operators must start with '$'");
        }
        // constant or variable
        switch (occurrences.length) {
            case 0:
                try {
                    exp.constant = Integer.parseInt(operator.substring(1));
                    exp.type = ExpType.CONST;
                } catch (NumberFormatException e) {
                    exp.var = operator.substring(1);
                    exp.type = ExpType.VAR;
                }                        
                break;
            case 2:
                if (!operator.equals("$+"))
                    throw new RuntimeException("Invalid integer binary expression: " + operator);
                exp.type = ExpType.OTIMES;
                exp.left = new Expression<Integer>(occurrences[0]);
                exp.right = new Expression<Integer>(occurrences[1]);
                break;
            case 3:
                if (!operator.equals("$ite"))
                    throw new RuntimeException("Invalid integer binary ternary: " + operator);
                exp.type = ExpType.ITE;
                exp.condition = new Expression<Boolean>(occurrences[0]);
                exp.left = new Expression<Integer>(occurrences[1]);
                exp.right = new Expression<Integer>(occurrences[2]);
                break;
            default: 
                throw new RuntimeException("Invalid number of occurrences for Integer production: " + occurrences.length);
        } 

        return exp;
    }
    
    public static Expression<Boolean> inferBoolExpression(String operator, String[] occurrences){
        Expression<Boolean> exp = new Expression<Boolean>();
        // assume all operators begin with $
        if (operator.charAt(0) != '$') {
            throw new RuntimeException("All grammar operators must start with '$'");
        }
        switch (occurrences.length) {
            case 0: // constant or variable
                if (operator.equals("$t")) {
                    exp.type = ExpType.CONST;
                    exp.constant = true;
                }
                else if (operator.equals("$f")) {
                    exp.type = ExpType.CONST;
                    exp.constant = false;
                }
                else {
                    exp.type = ExpType.VAR;
                    exp.var = operator.substring(1);
                }
                break;
            case 1:
                if (!operator.equals("$not"))
                    throw new RuntimeException("Invalid boolean unary expression: " + operator);
                exp.bop = "not";
                exp.type = ExpType.NOT;
                exp.left = new Expression<Boolean>(occurrences[0]);
                break;
            case 2:
                exp.type = ExpType.BOOL;
                exp.bop = operator.substring(1);
                if (operator.equals("$and") || operator.equals("$or")) {
                    exp.left = new Expression<Boolean>(occurrences[0]);
                    exp.right = new Expression<Boolean>(occurrences[1]);
                }
                else if (operator.equals("$>=") || operator.equals("$<=") || operator.equals("$<") || operator.equals("$>")) {
                    exp.left = new Expression<Integer>(occurrences[0]);
                    exp.right = new Expression<Integer>(occurrences[1]);
                }
                else {
                    throw new RuntimeException("Invalid boolean binary expression: " + operator);
                }
                break;
            default: 
                throw new RuntimeException("Invalid number of occurrences for Boolean production: " + occurrences.length);
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

enum ExpType {
    CONST,
    VAR,
    OTIMES,
    OPLUS,
    ITE,
    BOOL,
    NOT
}