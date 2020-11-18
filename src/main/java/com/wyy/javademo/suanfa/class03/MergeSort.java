package com.wyy.javademo.suanfa.class03;

/**
 * 归并排序时间复杂度 ：O(N * logN)
 * 将一个数组在L-R上有序
 * 归并排序：采用二分递归的方式，先保证左边的数据有序，再保证右边的数据有序
 * 最后合并两边的数据，保证整个数组的数据有序
 *
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr = {1,3,5,2,6,8,3};
        process(arr,0,6);
        for (int i=0 ;i<arr.length ;i++){
            System.out.print(""+ i ) ;
        }

    }



    /**
     * 保证数组在L-R上有序
     * 1、线保证L-M上有序
     * 2、保证M+1-R上有序
     * 3、合并二者的结果，保证L-R上有序
     */
    public static void process(int[] array,int L,int R){

        if(L == R){
            return;
        }

        int M = L + ((R-L) / 2 >> 1);
        process(array, L, M);
        process(array, M+1, R);
        mergeSort(array, L, M, R);
    }

    public static void mergeSort(int[] array,int L,int M, int R){
        int[] copyArray = new int[R - L + 1]; //新建一个辅助的数组用于拷贝有序数组

        int P1 = L;
        int P2 = M + 1;
        int i = 0;
        while (P1 <= M && P2 <= R){
            //谁小拷贝谁，最后一定有一边被先拷贝完。
            copyArray[i++] = array[P1] <= array[P2] ? array[P1++] : array[P2++];
        }

        while(P1 <= M){
            copyArray[i++] = array[P1++];
        }

        while (P2 <= R){
            copyArray[i++] = array[P2++];
        }

        for( i = 0 ; i < copyArray.length; i++){
            array[L + i] =copyArray[i];
        }
    }
}
