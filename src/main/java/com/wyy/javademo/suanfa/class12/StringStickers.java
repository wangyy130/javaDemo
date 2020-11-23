package com.wyy.javademo.suanfa.class12;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串贴纸的问题
 *
 * 给定一个目标字符串target
 * 数组中给定几个字符串arr[],每个字符串都可以当成一个贴纸，每个字符串都可以拆开成单个字符
 *
 * 问至少多少张贴纸可以组成target字符串
 *
 *
 * 整体思路，target其实不分顺序，只要组成的字母个数够就可以，
 * 所以可以将arr中的贴纸都拆分成一个字符计数的二维数组
 * 而将target拆分成一个字符计数的一维数组
 *
 * 暴力递归的两种思路：
 * 1、遍历数组中每一个贴纸，去组成target，看剩下的target还有哪些，再去遍历数组的每一个贴纸，再去组成剩余的target
 *      只有一个变量就是rest
 * 2、第一轮枚举用数组中的第一个贴纸，遍历所有可能的张数
 *      第二轮枚举数组中第二个贴纸，遍历所有可能的张数
 *     target = aaaabbbc , arr =[aa,bb,cc]
 *     aa最多可能用2张，所以第一轮遍历 aa， 从0 -2
 */
public class StringStickers {

    //方法1 ： 每次遍历数组中的每个贴纸看有多少方案，求最小的就可以了
    public int ways1(String[] arr, String target){
        if(arr == null || arr.length == 0 || target == null){
            return 0;
        }

        int N = arr.length;
        int[][] amap = new int[N][26];

        //初始化amap
        for(int i = 0; i < N; i++){
            char[] chars = arr[i].toCharArray();
            for(char s : chars){
                amap[i][s - 'a']++;
            }
        }
        //缓存
        Map<String,Integer> dp = new HashMap<>();
        dp.put("",0); //隐藏的baseCase,当剩余字符串为"",有0种方案
        return process(amap,dp,target);
    }


    /*
        dp: 缓存，如果剩余字符串 rest 已经统计过了，则放入dp中，下次直接取
        target: 目标字符串
        amap : 数组中的贴纸，值永远不变
     */
    public int process(int[][] amap, Map<String,Integer> dp,String target){
        //先判断缓存有没有
        if(dp.containsKey(target)){
            return dp.get(target);
        }

        //目标字符串的字符计数表
        int[] restMap = new int[26];
        char[] targetArray = target.toCharArray();
        //初始化target计数表
        for(char c : targetArray){
            restMap[c - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        //遍历每一个数组中的贴纸
        for(int i = 0; i < amap.length; i++){

            StringBuilder sb = new StringBuilder();
            //这个判断是为了保证每一次循环中，贴纸包含目标字符串中的字符，如果不包含，直接跳下一个贴纸
            if(amap[i][targetArray[0] - 'a'] == 0){
                continue;
            }


            //开始去求剩余的target了，但是这里不需要让贴纸数减，因为贴纸可以重复利用
            for(int j = 0; j < 26; j++){
                if(restMap[j] > 0){
                    for(int k = 0; k < Math.max(restMap[j] - amap[i][j], 0); k++ ){
                        sb.append('a' + k);
                    }
                }
            }

            //求剩余字符串有多少中拼接方案
            int next = process(amap, dp, sb.toString());

            if(next !=  -1){
                ans = Math.min(ans, next + 1);
            }

        }

        dp.put(target, ans == Integer.MAX_VALUE  ? -1 : ans);
        return ans;

    }




}
