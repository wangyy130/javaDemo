package com.wyy.javademo.suanfa.class01;

/**
 * 插入排序
 */
public class InsertSort {


    public static void insertSort(int[] array){

        if(array==null || array.length<2){
            return;
        }

        /**
         * 插入排序
         * 0~0 直接有序
         * 0~1 有序
         */
        for(int i = 1 ; i < array.length; i++){ //做到前i个数有序

            /**
             * array[j+1]能不能换成array[i] 呢 不可以，因为arra[i] 的值随着交换的发生会一直变化，这里array[j]总是应该跟交换完之后最小的值进行交换
             *
             * 这里j >=0
             */
            for(int j = i-1 ; j >= 0 && array[j] > array[j+1] ;j-- ){
                //交换
                swap(array,j,j+1);
            }
        }
    }


    public static void swap(int[] array,int i,int j){
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    static void print(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

        insertSort(arr);

        print(arr);
    }
}
