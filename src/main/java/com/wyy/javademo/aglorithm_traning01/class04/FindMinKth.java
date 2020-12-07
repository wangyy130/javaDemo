package com.wyy.javademo.aglorithm_traning01.class04;


/*
    bfprt算法 找第K小的数

    1、基于快速排序，随机指定一个位置的数
    2、大根堆的方式
    3、精挑细选一个数，不再基于概率，最终时间复杂度O(N)
 */
public class FindMinKth {

    public static int findMinKth1(int[] arr, int K){
       int[] copy = copyArray(arr);
       return process1(copy,0, copy.length - 1, K - 1);
    }

    /*
        改写快排
     */
    public static int process1(int[] arr, int L, int R, int index){
        //BaseCase：数组中只有一个数
        if(L == R){
            return arr[L];
        }

        int pos = L + (int) (Math.random() * (R - L + 1));
        int[] range = partition(arr,L, R,arr[pos]);
        if(index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return process1(arr, L, range[0] - 1, index);
        }else{
            return process1(arr,range[1] + 1, R, index);
        }
    }


    /*
        bfprt算法
     */
    public static int findMinKth2(int[] arr, int K){
        int[] copy = copyArray(arr);

        return bfprt(copy, 0 , arr.length - 1, K - 1);
    }


    public static int bfprt(int[] arr, int L, int R, int index){
        if(L == R){
            return arr[L];
        }

        int pos = getMedianOfMedians(arr, L, R);
        int[] range = partition(arr,L, R, pos);

        if(index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return bfprt(arr, L, range[0] - 1, index);
        }else {
            return bfprt(arr, range[1] + 1, R, index);
        }

    }


    /*
        将数组分成5个数一组，每组5个数按顺序排好，将排好的数中每组选一个中位数组成一个新的数组
        获取新数组的中位数并返回
     */
    public static int getMedianOfMedians(int[] arr, int L, int R){
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;

        int[] mArr  = new int[size / 5 + offset];

        for(int team = 0; team < mArr.length; team++){
            int teamFirst = L + team * 5 ;
            mArr[team] = getMidan(arr, teamFirst, Math.min(teamFirst + 4, R));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length/ 2);
    }

    //排序并获取数组的中位数
    public static int getMidan(int[] arr, int L, int R){
        insertSort(arr, L, R);
        return arr[(L + R) / 2];
    }


    public static void insertSort(int[] arr, int L, int R){
        for(int i = L + 1; i <= R; i++){
            for(int j = i - 1; j >= L && arr[j] > arr[j + 1] ; j--){
                swap(arr, j, j + 1);
            }
        }
    }

    public static int[] partition(int[] arr, int L, int R, int num){
        int less = L - 1;
        int more = R + 1;
        int index = L;

        while (index < more){
            if(arr[index] < num){
                swap(arr, ++less, index++);
            }else if(arr[index] > num){
                swap(arr, --more, index);
            }else{
                index++;
            }
        }

        return new int[]{less + 1, more - 1};

    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int[] copyArray(int[] arr){
        int[] copy = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            copy[i] = arr[i];
        }

        return copy;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4,8,1,6,3,9,2};
        System.out.println(findMinKth1(arr,3));
        System.out.println(findMinKth2(arr,3));
    }
}
