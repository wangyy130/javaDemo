package com.wyy.javademo.aglorithm_traning01;

import java.util.LinkedList;

/**
 * 达标子数组个数的问题
 *
 * 要建立单调性
 * 大范围上满足条件，小范围上也一定满足条件
 */
public class SubArray_02 {

    public static  int MaxSubArray(int[] arr,int num){
        if(arr == null || arr.length == 0 ){
            return 0;
        }
        LinkedList<Integer> maxQ = new LinkedList<>();
        LinkedList<Integer> minQ = new LinkedList<>();

        int L = 0;
        int R = 0;
        int res = 0;
        //从0开始尝试，以L 开头的数组达标的有多少个
        while (L < arr.length ){
            System.out.println("L = " + L);
            //寻找以L开头的数组，R结尾满足条件的位置，R最终为第一个违规的位置，R的前一个位置为最终达标的位置
            while (R < arr.length){

                System.out.println("R = " + R);
                //每次R向右移时，添加R时，要先移除队列中比自己大或者小的数据
                while (!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[R]){
                    maxQ.pollLast();
                }
                maxQ.addLast(R);

                while (!minQ.isEmpty() && arr[minQ.peekLast()] >= arr[R]){
                    minQ.pollLast();
                }

                minQ.addLast(R);

                //如果不符合条件直接跳出R循环，R来到了第一个违规位置
                if(arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] > num){
                    break;
                }

                R++;
            }

            //到这里，以L开头，到R位置为符合条件的数组
            res += R - L;

            //马上L要++了，那么过期的位置要移除队列了
            if(maxQ.peekFirst() == L){
                maxQ.pollFirst();
            }

            if(minQ.peekFirst() == L){
                minQ.pollFirst();
            }
            L++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,3,7,8,1,6};
        int num = 3;
        System.out.println(MaxSubArray(arr,num));
    }
}
