package com.wyy.javademo.suanfa.class09;

//金条分割问题
//贪心算法可以用小根堆的方式解决
public class GoldSplit {


    //暴力递归的方式
    public int lessMoneySplit(Integer[] arrs){
        if(arrs == null || arrs.length == 0){
            return 0;
        }

        return  process(arrs, 0);
    }
    // pre总共花费的总量
    public int process (Integer[] arrs,int pre){
        if(arrs.length == 1){
            return  pre;
        }else{

            int min = Integer.MAX_VALUE;
            //还有金条需要被分割
            for(int i = 0; i < arrs.length; i++){
                for(int j = i + 1; j < arrs.length ; j++){
                    min = Math.min(min,process(mergeArrys(arrs,i,j),pre+ arrs[i] + arrs[j]));
                }
            }

            return  min;
        }
    }


    //将i位置和j位置的数合并后组成一个新的数组
    public Integer[] mergeArrys(Integer[] arrs,int i, int j){
        Integer[] ans = new Integer[arrs.length - 1];
        int index = 0;
        for(int k = 0; k < arrs.length ; k++){
            if(k != i && k != j){
                ans[index++] = arrs[k];

            }
        }

        ans[index] = arrs[i] + arrs[j];
        return  ans;
    }
}
