package com.wyy.javademo.suanfa.class01;

/*
冒泡排序
 */
public class BubbleSort {

    public static void bubbleSort(int[] array){

        if(array==null || array.length<2){
            return ;
        }

        /**
         * 要找出第1个最大的数也就是第n个位置的数，需要先进行从0 - n-1 进行比较
         * 0 - n-1  第n个数
         * 0 - n-2  第n-1个数
         * 0 - n-3
         *
         * 获取最小位置第0位的数为终止条件，即最小值 i--=0 ，那么i>0
         */
        for(int i = array.length - 1;  i > 0; i--){

            // 0 1
            // 1 2
            // 2 3
            // j j+1 即 i-1和i进行交换
            for(int j = 0 ; j < i ; j++){
                if(array[j] > array[j+1]){
                    //交换两个位置的数
                    swap(array, j,j+1);
                }
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

        bubbleSort(arr);

        print(arr);
    }
}
