package com.wyy.javademo.Leetcode;

public class LeetCode_164 {

    public static int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        //排序
        sort(nums, 0, nums.length - 1);

        int maxGap = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++){
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }

        return maxGap;

    }

    //快速排序,递归，要有basecase
    public static void sort (int[] nums,int L, int R){
        //basecase
        if(L > R){
            return;
        }
        int[] res = process(nums,L,R);
        sort(nums,L, res[0] - 1);
        sort(nums, res[1] + 1, R);

    }

    public static int[] process(int[] nums, int L, int R){
        if(L == R){
            return new int[]{L, R};
        }

        if(L > R){
            return new int[]{-1, -1};
        }
        int less = L - 1;
        int more = R ;
        int index = L;
        while(index < more){
            if(nums[index] < nums[R]){
                swap(nums, ++less, index++);
            }else if(nums[index] > nums[R]){
                swap(nums, index, --more);
            }else{
                index++;
            }
        }
        swap(nums, more, R);

        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,6,9,1};
        System.out.println(maximumGap(nums));
    }
}
