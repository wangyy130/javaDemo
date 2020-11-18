package com.wyy.javademo.suanfa.class03;

import javax.naming.PartialResultException;

/**
 * 归并求小和问题
 *
 * 在数组 L-R范围内产生的所有小和问题
 *
 * 1、先求出L-M上的所有小和 leftSum
 * 2、再求出M-R上的所有小和 rihgtSum
 * 3、再求出L-R上的所有小和 sum
 * 4、最后leftSum+rightSum+sum
 */
public class SmallSum {


    public static void main(String[] args) {

        int[] arr = {1,3,5,2,6,8,3};
        System.out.printf("L - R上小和为" + getSmallSum(arr,0 , 6));
    }



    public static int getSmallSum(int[] array, int L, int R){
        if(L == R){
            return 0;
        }

        int M = L + ((R - L) / 2 >>1);

        int leftSum = getSmallSum(array , L ,M);
        int rightSum = getSmallSum(array , M+1, R);
        int sum = mergeSum(array , L , M, R);

        return leftSum + rightSum + sum;
    }


    public static int mergeSum(int array[], int L, int M, int R){

        int[] help =new int[R - L + 1];

        int i = 0;
        int P1 = L;
        int P2 = M + 1;
        int res = 0;

        while (P1 <= M && P2 <= R){
            //当P1小于P2时，则计算右边有多少个数大于P1，则会产生多少个P1的小和
            res += array[P1] < array[P2] ? (R - P2 + 1) * array[P1] : 0 ;
            help[i++] = array[P1] < array[P2] ? array[P1++] : array[P2++] ;
        }

        while (P1 <= M){
            help[i++] = array[P1++];
        }

        while (P2 <= R){
            help[i++] = array[P2++];
        }

        for( i = 0 ; i < help.length; i++){
            array[L+ i] = help[i];
        }

        return res;
    }

}
