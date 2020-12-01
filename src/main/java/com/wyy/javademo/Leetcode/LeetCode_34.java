package com.wyy.javademo.Leetcode;


/**
 * 给定一个升序数组，和一个target，寻找target在数组中匹配上的最小和最大位置
 * 要求时间复杂度为 O(logN)
 */
public class LeetCode_34 {

    public int[] searchRange(int[] nums, int target) {

        if(nums.length == 0){
            return new int[]{-1, -1};
        }

        if(nums.length == 1 && nums[0] == target){
            return new int[]{0,0};
        }

        int N = nums.length;
        int L = 0;
        int R = N - 1;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(L < R){
            int M = L + ((R - L) >> 1);

            if(nums[M] < target){
                L = M + 1;
            }else if(nums[M] > target){
                R = M - 1;
            }else {

                //如果相等，需要判断，前一个位置和后一个位置是否也相等
                int i = M;
                int j = M;
                while(i >= L){
                    if(target == nums[i]){
                        min = Math.min(min, i);
                        max = Math.max(max,i);
                        i--;
                    }else{
                        break;
                    }
                }

                while(j <= R){
                    if(target == nums[j]){
                        min = Math.min(min, j);
                        max = Math.max(max, j);

                        j++;
                    }else{
                        break;
                    }

                }

                return new int[]{min,max};

            }
        }

        return target == nums[L] ? new int[] {L,L} : new int[]{-1, -1};

    }

}
