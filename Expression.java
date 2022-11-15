/* Modified from original Nay solver
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
public class Expression<E> {
    /*
        0: const
        1: var
        2: dot
        3: plus
        4: ite
        5: bool
        6: boolu
     */
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

    public Expression dotExpression(Expression left, Expression right) {
        return new Expression(ExpType.DOT, left, right);
    }
    
    public Expression inferExpression(String operator, String occurrences){
    }

    @Override
    public String toString(){
        String result = "";
        if(this.type == ExpType.DOT)
            return left.toString()+"(*)"+right.toString();
        if(this.type == ExpType.PLUS)
            return left.toString() + "(+)" + right.toString();
        if(this.type == ExpType.CONST )
            return this.constant.toString();
        if(this.type == ExpType.VAR)
            return this.var.toString();
        if(this.type == ExpType.ITE)
            return "(ITE ("+ this.condition.toString()+") " +" ("+this.left.toString()+") "+ " ("+this.right.toString()+") "+")";
        if(this.type == ExpType.BOOL)
            return this.left.toString()+" "+this.bop +" "+this.right.toString();
        if(this.type == ExpType.NOT)
            return this.bop +" "+this.left.toString();
        System.out.println("ERROR: invalid expression type: " + this.type.toString());
        return null;
    }
}

enum ExpType {
    CONST,
    VAR,
    DOT,
    PLUS,
    ITE,
    BOOL,
    NOT
}