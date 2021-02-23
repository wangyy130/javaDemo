package com.wyy.javademo.aglorithm_traning01.class10;

import java.util.HashMap;
import java.util.Map;

/*
    和1的问题类似，但是数组中的元素有整数，负数和0
    此问题不具有单调性

    思路： 求以数组中每个元素结尾的累加和 == K的最长子数组问题

    从0到i的累积和为sum，sum - K 就是从0到x位置累加和为sum - K

    我们用map来记录累加和为sum -K的最早的累加和的位置x

    这样以i位置元素结尾的最长子数组累加和为K就能求出最大的长度 就是 i - x
 */
public class LongestSumSubArrayLength_02 {

    public static int getMaxLength(int[] arr, int K){
        if(arr == null || arr.length == 0){
            return 0;
        }

        int len = 0;
        Map<Integer,Integer> map = new HashMap<>();
        //加这行是为了，如果出现从0开始就满足条件的情况，会将0位置为忽略到导致长度-1
        map.put(0,-1);

        int sum = 0; //累加和
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(map.containsKey(sum - K)){
                len = Math.max(len, i - map.get(sum - K));
            }

            if(!map.containsKey(sum )){
                map.put(sum, i);
            }
        }

        return len;
    }
}
