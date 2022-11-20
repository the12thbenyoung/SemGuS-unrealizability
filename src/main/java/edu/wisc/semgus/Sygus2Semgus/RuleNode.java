/* Taken from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.Sygus2Semgus;

import java.util.ArrayList;
import java.util.List;

public class RuleNode extends ProgramNode {
    List<String> weight;

    public String getSymbol() {
        return content.symbol;
    }


    public List<GTermNode> getChildren() {
        return content.children;
    }

    public GTermNode getContent() {
        return content;
    }

    public void setContent(GTermNode content) {
        this.content = content;
    }

    GTermNode content;

    public RuleNode(List<String> weight, GTermNode gterm){
        this.weight = weight;
        if(weight == null)
            this.weight = new ArrayList<String>();
        content = new GTermNode(gterm.getSymbol(),gterm.getChildren());
    }

    public RuleNode( GTermNode gterm){
        this(new ArrayList<>(),gterm);
    }
    @Override
    public String toString(){
        if (content.children == null)
            return content.symbol;
        String result = "( " + content.symbol + " ";
        for(GTermNode node: content.children)
            result = result + node.toString() + " ";
        return result + ")";
    }
}
