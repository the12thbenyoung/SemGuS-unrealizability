/* Taken from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.Sygus2Semgus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TermNode extends ProgramNode {
    String symbol;
    List<TermNode> children;


    public TermNode(String symbol, List<TermNode> children){
        this.symbol = symbol;
        this.children = children;
    }

    public TermNode(String symbol){
        this(symbol,new ArrayList());
    }
    public TermNode(String symbol, TermNode... t){
        this(symbol,new ArrayList<>());
        this.children.addAll(Arrays.asList(t));
    }



    public boolean hasChild(){
        return children != null && children.size() != 0;
    }
    @Override
    public String toString(){
        String result = this.symbol+"(";
        for(TermNode child: this.children){
            result+= child.toString();
        }
        return result+")";
    }

    public String getSymbol() {
        return symbol;
    }

    public List<TermNode> getChildren() {
        return children;
    }

    public void setSymbol(String symbol){this.symbol = symbol;}
}
