package com.wyy.javademo.suanfa.class05;

/**
 * 桶排序 - 计数排序
 */
public class CountSort {

    //计数排序
    //limit 为数组中数据的范围最大值
    public static void countSort(int [] array,int limit){
        int [] buckets = new int[]{limit + 1};

        for (int index : array) {
            buckets[index]++;
        }

        int j = 0;
        for(int i = 0 ;i < buckets.length ; i++){
            int count = buckets[i];
            //这里这个写法很经典
            while (count-- > 0 ){
                array[j++] = i;
            }

        }
    }
}
