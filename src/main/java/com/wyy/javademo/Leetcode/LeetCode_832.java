package com.wyy.javademo.Leetcode;

public class LeetCode_832 {

    public int[][] flipAndInvertImage(int[][] A) {
        int m = A.length; //m行数据
        int n = A[0].length; //n列
        int k = n%2 == 0 ? (n/2 - 1)  : (n/2) ; //用于逆序的中点
        for(int i = 0; i < m; i++){
            for(int j = 0; j <= k; j++){
                swap(A[i], j,n - j-1);

            }
        }
        return A;
    }

    private void swap(int[] arr, int i, int j){
        if(i == j){
            arr[i] = arr[i] ^ 1;
        }else{
            int temp = arr[i];
            arr[i] = arr[j] ^ 1;
            arr[j] = temp ^ 1;
        }

    }

}
