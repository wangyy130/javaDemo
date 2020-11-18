package com.wyy.javademo.suanfa.class01;

/**
 * 二分查找
 */
public class BinarySearch {

    public static boolean binarySearch(int[] sortedArray,int num){

        int left = 0 ;
        int right = sortedArray.length - 1;
        int mid = 0;

        while (left < right){

//            mid=(left + right) / 2 ; //获取中位数

            mid = left + (right - left) >> 1 ;

            if(sortedArray[mid] == num){
                return true;
            } else if (sortedArray[mid] > num){
                right = mid - 1;
            } else if (sortedArray[mid] < num){
                left = mid + 1;
            }

        }

        return false;
    }
}
