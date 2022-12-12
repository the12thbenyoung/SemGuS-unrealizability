/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.fixedpoint;

import java.util.*;
import edu.wisc.semgus.utilities.Equation;
import edu.wisc.semgus.utilities.Expression;

// for stratification
public class DAG {

    private Set<DAGNode> root = new HashSet<>();
    public Set<DAGNode> nodes = new HashSet<>();
    public Set<DAGNode> getRoot() {
        return root;
    }
    public Set<String> popRoot() {
        Set<DAGNode> candidate = new HashSet<>(nodes);
        Set<DAGNode> invalid= new HashSet<>();
        for(DAGNode node: nodes){
            invalid.addAll(node.successors);
        }
        candidate.removeAll(invalid);
        if(candidate.isEmpty())
            return null;
        DAGNode pop = candidate.iterator().next();
        nodes.remove(pop);
        return pop.getValue();
    }

    public DAG(List<Equation> eqs){

        Map<String,Set<String>> reached_list = new HashMap<>();
        for(Equation eq: eqs){
            Set<String> tmp_list = getReachedInExpression(eq.right);
            reached_list.put(eq.left,tmp_list);
        }

        int totalReached = 0;
        while (true){
            int currentTotal = 0;
            for(String key:reached_list.keySet()){
                currentTotal+= reached_list.get(key).size();
            }
            if(totalReached==currentTotal)
                break;
            totalReached = currentTotal;
            for(int j = 0; j < eqs.size();j++){
                Set<String> toAdd = new HashSet<>();
                    for(String currentStr:reached_list.get(eqs.get(j).left)){
                        toAdd.addAll(reached_list.get(currentStr));
                    }
                reached_list.get(eqs.get(j).left).addAll(toAdd);
            }
        }
        Set<String> covered = new HashSet<>();
        Set<Set<String>> eqclasses = new HashSet<>();
        for(int j = 0; j < eqs.size();j++){
            if(covered.contains(eqs.get(j).left))
                continue;
            Set<String> newEqClass = new HashSet<>();
            newEqClass.add(eqs.get(j).left);
            for(String right: reached_list.get(eqs.get(j).left)){
                if(reached_list.get(right).contains(eqs.get(j).left)) {
                    newEqClass.add(right);
                    covered.add(right);
                }
            }
            eqclasses.add(newEqClass);
        }
        for(Set<String> eqc: eqclasses){
            this.nodes.add( new DAGNode(eqc));
        }
        for(DAGNode nodeFrom:this.nodes){
            for(DAGNode nodeTo:this.nodes){
                if(nodeFrom==nodeTo)
                    continue;
                for(String strFrom: nodeFrom.getValue()){
                    for(String strTo: nodeTo.getValue()){
                        if(reached_list.get(strFrom).contains(strTo)){
                            nodeTo.addSuccessor(nodeFrom);
                            break;
                        }
                    }
                }
            }
        }
        return;
    }

    public Set<String> setDiff(Set<String> set1, Set<String> set2){
        Set<String> tmp1 = new HashSet<>(set1);
        tmp1.removeAll(set2);
        return tmp1;
    }


    @Override
    public String toString(){
        String result = this.root.toString();

        return result;
    }

    Set<String> getReachedInExpression(Expression exp){
        Set<String> result = new HashSet<>();
        switch (exp.type){
            case CONST: return  result;
            case VAR: result.add(exp.var);
                return result;
            case ITE:
                result.addAll(getReachedInExpression(exp.left));
                result.addAll(getReachedInExpression(exp.right));
                result.addAll(getReachedInExpression(exp.condition));
                return result;
            case OTIMES:
            case OPLUS:
            case BOOL:
                result.addAll(getReachedInExpression(exp.left));
                result.addAll(getReachedInExpression(exp.right));
                return result;
            case NOT:
                result.addAll(getReachedInExpression(exp.left));
                return result;
        }
        System.out.println("ERROR: wrong type getReachedInExpression: "+exp.toString());
        return null;
    }

    // -------------------------------
    // -------------------------------
    // -------------------------------

    public class DAGNode{
        public Set<DAGNode> getSuccessors() {
            return successors;
        }

        private Set<DAGNode> successors;

        public Set<String> getValue() {
            return value;
        }

        public void setValue(Set<String> value) {
            this.value = value;
        }

        private Set<String> value;
        // 0 = bool
        // 1 = int
        private int type;


        public void addSuccessor(DAGNode node){
            this.successors.add(node);
        }
        public void addSuccessors(Set<DAGNode> nodes){
            this.successors.addAll(nodes);
        }

        public DAGNode( Set<String> val){
            this.value = val;
            this.successors = new HashSet<>();
        }
    }

    // -------------------------------
    // -------------------------------
    // -------------------------------
}
