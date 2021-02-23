package com.wyy.javademo.aglorithm_traning01.class10;

/*
    给定一个数组，数组中都是正数
    给定一个值K
    求数组中子数组累加和 = K的最长子数组的长度

    分析： 该数组具有单调性，可以用滑动窗口来解
 */
public class LongestSumSubArrayLength_01 {

    public static int getMaxLength(int[] arr,int K){
        if(arr == null || arr.length == 0 || K == 0){
            return 0;
        }
        int L = 0;
        int R = 0;
        int sum = arr[0];
        int len = 0;

        while (R < arr.length){
            if(sum == K){
                //相等的时候L或者R往右扩都可以
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            }else if(sum > K){
                sum -= arr[L++];
            }else{
                R++;
                if(R == arr.length){
                    break;
                }
                sum += arr[R];
            }
        }
        return len;
    }
}
