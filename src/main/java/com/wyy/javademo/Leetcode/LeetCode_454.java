package com.wyy.javademo.Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加问题
 */
public class LeetCode_454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> countAB = new HashMap<>();

        for(int u : A){
            for(int v : B){
                countAB.put(u + v , countAB.getOrDefault(u + v, 0) + 1);
            }
        }

        int res = 0;
        for(int u : C){
            for(int v : D){
                if(countAB.containsKey(-(u + v))){
                    res += countAB.get(-(u + v));
                }
            }
        }

        return res;
    }
}
