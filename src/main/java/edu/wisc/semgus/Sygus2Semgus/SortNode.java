/* Taken from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.Sygus2Semgus;

public class SortNode extends ProgramNode {
    public final static int BOOL =0;
    public final static int INT =1;
    public final static int REAL =3;
    public final static int BV =2;

    private int type;
    private int length;

    public SortNode(int type, int length){
        this.type = type;
        this.length = length;
    }
    public SortNode(int type){
        this(type,0);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString(){
        return "type "+ type +" length "+length;
    }
}
