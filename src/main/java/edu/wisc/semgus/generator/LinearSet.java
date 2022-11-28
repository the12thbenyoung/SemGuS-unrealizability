package edu.wisc.semgus.generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class LinearSet {
    Integer dimension;
    Vector<Integer> base;
    Set<Vector<Integer>> period;

    public LinearSet(Integer dimension, Vector<Integer> base, Set<Vector<Integer>> period) {
        this.dimension = dimension;
        this.base = base;
        this.period = period;
    }
    public LinearSet( Vector<Integer> base, Set<Vector<Integer>> period) {
        this(base.size(), base, period);
    }

    public LinearSet(Vector<Integer> base){
        this(base.size(), base, new HashSet<>());
    }

    public LinearSet( Integer constant, Integer dim) {
        this(makeConstantVector(constant, dim));
    }

    private static Vector<Integer> makeConstantVector(Integer constant, Integer size) {
        Vector result = new Vector<>();
        for(int i = 0; i < size; i++){
            result.add(constant);
        }
        return result;
    }

    public LinearSet clone(){
        return new LinearSet(dimension,(Vector<Integer>) base.clone(),(HashSet)((HashSet)this.period).clone());
    }


    public LinearSet dicSum(LinearSet w){
        Vector newBase = new Vector();
        for(int i = 0; i < dimension; i++){
            newBase.add(this.getBase().get(i)+w.getBase().get(i));
        }
        Set newPeriod = (HashSet)((HashSet)period).clone();
        newPeriod.addAll((HashSet)((HashSet)w.getPeriod()).clone());
        return new LinearSet(dimension,newBase,newPeriod);
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Vector<Integer> getBase() {
        return base;
    }

    public void setBase(Vector<Integer> base) {
        this.base = base;
    }

    public Set<Vector<Integer>> getPeriod() {
        return period;
    }

    public void setPeriod(Set<Vector<Integer>> period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o){
        Vector<Integer> obase = ((LinearSet)o).base;

        Set<Vector<Integer>> op= ((LinearSet)o).period;
        return this.base.equals(obase) && this.period.equals(op);
    }

    public String toString(){
        String result = "(";
        result+="base: "+base+", ";
        result+="period: "+period+")";
        return  result;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result + ((base == null) ? 0 : base.hashCode());
        result = prime*result + ((period == null) ? 0 : period.hashCode());
        return  result;
    }
}
