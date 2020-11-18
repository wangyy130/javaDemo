package com.wyy.javademo.suanfa.class09;

import java.util.HashSet;

/**
 * 在一条路上放多少盏灯可以照亮整条路
 */
public class LightsTest {

    //暴力递归解法
    public int arrangeLight(String road){
        return process(road.toCharArray(),0,new HashSet<>());
    }

    //strs 代表这条路上每个 '.' 或 'X'
    //index代表当前遍历到哪个位置
    //所有点放灯的位置加入到lights中
    public int process(char[] strs,int index, HashSet<Integer> lights){
        //如果index遍历到最后一个位置时，那么要判断当前的这种方案是否能够照亮所有的 点
        if(index == strs.length){

            for(int i = 0 ; i < strs.length ; i++){
                if(strs[i] != 'X'){
                    if(!(lights.contains(i - 1) && lights.contains(i) && lights.contains(i+1))){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else{
            //还没有遍历完一条路，继续遍历

            //当前是点或是‘X’都可以不放灯，所以直接跳过去安排下个位置
            int no = process(strs, index + 1, lights);

            int yes = Integer.MAX_VALUE;

            if(strs[index] == '.'){
                lights.add(index);
                yes = process(strs,index + 1, lights);
                lights.remove(index);
            }

            return Math.min(yes,no);
        }
    }
}
