public class Equation<E> {
    public String left;
    public Expression<E> right;
    public EqType type;

    public Equation(String left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public Equation(String left, Expression right, EqType type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    @Override
    public String toString(){
        return this.left +" = "+this.right;
    }
}

enum EqType {
    INT,
    BOOL
}