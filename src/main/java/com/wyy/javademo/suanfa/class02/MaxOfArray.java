package com.wyy.javademo.suanfa.class02;

/**
 * 递归方式在array中L-R范围内求最大值
 *
 *1、先求L-M中的最大值 leftmax
 * 2、再求M-R的最大值 rightmax
 * 3、求L-R的最大值 leftmax和rightmax的最大值
 *
 */
public class MaxOfArray {

    public static int process(int[] array,int L,int R){
        //baseCase 当L==R时,返回L
        if(L == R){
            return array[L];
        }

        int M = L + ((R - L)/2 >> 1);
        int leftMax = process(array, L, M);
        int rightMax = process(array,M+1, R);
        return Math.max(leftMax,rightMax); //直接求最大值，如果是归并排序，这里会合并二者的结果
    }

    public static void main(String[] args) {
        int[] array={3,5,2,6,1,99,12,109,1,1,1,22,2,3};
        System.out.print("res = "+process(array,10,13));
    }

}
