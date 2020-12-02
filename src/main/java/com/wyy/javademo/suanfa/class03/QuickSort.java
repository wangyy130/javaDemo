package com.wyy.javademo.suanfa.class03;

/*
快速排序

1、在数组L - R范围上将<= num的值放在左边，>=num的值放在右边，num的值放在中间，num的位置M
如果num有多个，可以是一个数组的，num从M - N
2、在L-M位置上继续找，M-R位置上继续找或者从L - M-1和N+1到R上继续排序


 */
public class QuickSort {


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //以array[R] 作为标准，将小于的数放在左边，大于的数放在右边
    //求最后array[R]的位置
    public static int partition(int[] array, int L, int R){

        if(L > R){
            return -1;
        }

        if(L == R ){
            return L;
        }

        int index = L; //遍历的索引
        int less = L-1;

        while (index < R){

            if(array[index] <= array[R]){
                swap(array, ++less , index);
            }
            index++;
        }

        swap(array, ++less, R);
        return less;

    }

    /*
    快速排序1.0版本
     */
    public static void process1(int[] arr, int L, int R){
        int M = partition(arr,L,R) ;
        process1(arr, L , M);
        process1(arr, M+1, R);


    }

    //荷兰国旗问题解决方法
    //quicksort2.0方法，返回num位置的一个数组，从左和从右同时进行
    public static int[] netherlandsFlag(int[] array, int L, int R){
        if(L > R){
            return new int[]{-1, -1};
        }

        if(L == R){
            return new int[]{L, R};
        }
        int less = L - 1; //小数区的右边界
        int more = R;  //大数区的左边界
        int index = L;

        while (index < more){

            if(array[index] < array[R]){
                swap(array, ++less, index++);
            }else if(array[index] > array[R]){
                swap(array, --more,index);
            }else {
                index++;
            }
        }

        swap(array, more ,R);  //将R跟大数区第一个数进行交换

        return new int[]{less+1,more};

    }



    public static  void process2(int[] array ,int L, int R){

        int[] numPos = netherlandsFlag(array,L, R);
        process2(array,L,numPos[0]-1);
        process2(array,numPos[1]+1,R);
    }


    public static void main(String[] args) {
        int[] arr = {1,3,5,2,6,8,3};
        System.out.printf(""+partition(arr,0,6));
    }
}
