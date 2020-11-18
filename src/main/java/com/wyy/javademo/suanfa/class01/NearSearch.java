package com.wyy.javademo.suanfa.class01;

/**
 * 寻找一个有序数组中 >= value的最左位置
 * 寻找一个有序数组中 <= value 的最右位置
 */
public class NearSearch {

    /*
     >= value 最左位置
     */
    public static int nearLeftSearch(int[] sortedArray,int num){
        int left = 0;
        int right = sortedArray.length-1;
        int index = -1;

        while(left < right){

            int mid = left + ((right-left)/2 >> 1);

            if(sortedArray[mid] >= num){
                right = mid - 1;
                index = mid;

            }else{
                left = mid + 1;
            }

        }
        return index;
    }


    /**
     * <= value 最右位置
     *
     */
    public static int nearRightSearch(int[] sortedArray,int num){
        int left = 0;
        int right = sortedArray.length-1;
        int index = -1;

        while(left < right){

            int mid = left + ((right-left)/2 >> 1);

            if(sortedArray[mid] <= num){
                right = mid + 1;
                index = mid;

            }else{
                left = mid - 1;
            }

        }
        return index;
    }



}
