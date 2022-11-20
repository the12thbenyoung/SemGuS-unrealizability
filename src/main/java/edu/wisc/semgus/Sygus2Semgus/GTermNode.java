/* Taken from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.Sygus2Semgus;

import java.util.ArrayList;
import java.util.List;

public class GTermNode extends ProgramNode{
    public String getSymbol() {
        return symbol;
    }

    public String symbol;

    public List<GTermNode> getChildren() {
        return children;
    }

    public List<GTermNode> children;

    public GTermNode (String symbol, List<GTermNode> children){
        this.symbol = symbol;
        this.children = children;
    }
    public GTermNode (String symbol){
        this(symbol,new ArrayList<>());
    }

    @Override
    public String toString(){
        if (children == null)
            return symbol;
        String result = "( " + symbol + " ";
        for(GTermNode node: children)
            result = result + node.toString() + " ";
        return result + ")";
    }
}
