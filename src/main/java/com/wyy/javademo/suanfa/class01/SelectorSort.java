package com.wyy.javademo.suanfa.class01;

/**
 * 选择排序
 */
public class SelectorSort {

    public static void  selectSort(int[] array){

        if(array==null || array.length<2){
            throw new NullPointerException("数组为空");
        }


        // 1-n-1  获取第一个最小值 需要遍历从1到n-1进行循环比较
        // 2-n-1  获取第二个值，需要从2-n-1进行比较
        // 3-n-1

        //遍历获取第i个最小的数
        //n个数需要进行n-1次循环，最后一个数不需要进行比较
        for(int i = 0; i < array.length-1; i++){

            int minIndex = i;

            //循环遍历跟第J个数进行比较并交换位置
            //第0个数要分别跟第1，2,3 ... 直到第N个数进行比较和交换
            for(int j = i + 1; j < array.length; j++){
                minIndex=array[j] < array[minIndex] ? j : minIndex;
            }


            //交换
            swap(array,i,minIndex);

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

        selectSort(arr);

        print(arr);
    }

}
