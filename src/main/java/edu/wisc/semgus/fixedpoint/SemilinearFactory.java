/* Modified from implementation of Nay. Artifacts at
 * https://dl.acm.org/do/10.1145/3395631/full/
 */
package edu.wisc.semgus.fixedpoint;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class SemilinearFactory {
    static int dotCount = 0;
    public static Set<LinearSet> getEmpty(){
        return new HashSet<>();
    }

    public static Set<LinearSet> dot(Set<LinearSet> left, Set<LinearSet> right) {
        boolean isZero = false;
        if(left.size() == 0 || right.size() == 0)
            return new HashSet<>();
        if(left.size()==1&&left.iterator().next().getPeriod().size() ==0){
            for(Integer component: left.iterator().next().getBase()){
                if (component==0){
                    isZero = true;
                    break;
                }
            }
        }
        if(isZero)
            return right;

        if(right.size()==1&&right.iterator().next().getPeriod().size() ==0){
            for(Integer component: right.iterator().next().getBase()){
                if (component==0){
                    isZero = true;
                    break;
                }
            }
        }
        if(isZero)
            return left;


        Set<LinearSet> result = new HashSet<>();

        for(LinearSet lsLeft: left){
            for(LinearSet lsRight: right){
                result.add(lsLeft.dicSum(lsRight));
            }
        }
        dotCount++;

        return result;
    }

    public static Set<LinearSet> union(Set<LinearSet> left, Set<LinearSet> right) {
        Set<LinearSet> result = new HashSet<>();
        result.addAll(left);
        result.addAll(right);

        return result;
    }

    public static Set<LinearSet> star(Set<LinearSet> sl, int dim) {
        Set result = new HashSet();
        Vector  zeroBase = new Vector<>();
        for(int i = 0; i < dim; i++){
            zeroBase.add(0);
        }
        Set<Vector<Integer>> peroid = new HashSet<>();
        if(sl.size() == 0) {
            result.add(new LinearSet(dim, zeroBase, peroid));
            return result;
        }
        for(LinearSet lset: sl){
            if (lset.getPeriod().size() == 0 && isZero(lset.getBase()))
                continue;
            peroid.add(lset.getBase());
            peroid.addAll(lset.getPeriod());
        }
        LinearSet resultLS = new LinearSet(dim,zeroBase,peroid);
        result.add(resultLS);
        return result;
        
    }

    private static  Boolean isZero(Vector<Integer> bv){
        for(Integer b : bv)
            if(b != 0)
                return false;
        return true;
    }
}
