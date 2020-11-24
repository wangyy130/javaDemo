package com.wyy.javademo.aglorithm_traning01;

import java.util.Arrays;
import java.util.LinkedList;


/**
 * 滑动窗口最大值问题
 *
 * 滑动窗口：可以求一个可变窗口中的最大值和最小值
 */
public class SlidingWindowsOfMaxValue_01 {

    /**
     * 窗口依次划过数组，求每个窗口的最大值
     * @param arr 数组
     * @param W 窗口大小
     * @return 每个窗口的最大值
     */
    public static int[] maxValueOfWindow(int[] arr, int W){
        if(arr == null || arr.length == 0 || W < 1){
            return null;
        }

        //存放最大值的双端队列，队列中存放的是数组的下标!!!!!!!
        LinkedList<Integer> maxQ = new LinkedList<>();

        int[] res = new int[arr.length - W + 1];

        int index = 0;
        //依次将R位置的数加入双端队列
        for(int R = 0; R < arr.length; R++){
            //要先弹出才能添加
            while (!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[R]){
                maxQ.pollLast();
            }

            maxQ.addLast(R);

            //判断是否要弹出一个值
            assert maxQ.peekFirst() != null;
            if(maxQ.peekFirst().equals(R - W) ){
                maxQ.pollFirst();
            }

            //收集结果,从第W个数开始才收集结果
            if(R >= W - 1){
                assert maxQ.peekFirst() != null;
                res[index++] = arr[maxQ.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        int W = 3;
        System.out.println(Arrays.toString(maxValueOfWindow(arr,W)));

    }
}
