package com.wyy.javademo.suanfa.class11;


/**
 *
 * 从左到右的动态规划问题
 * 字符串转换的问题
 *
 * 1 - > A, 2 - > B ...
 *
 * 111 - > AAA,AK,KA
 *
 *
 * 给定一个只有数字的字符串，求有多少种转换方法
 */
public class ConvertStr {

    public int number(String strs){
        if(strs == null || strs.length() == 0){
            return  0 ;
        }

        return  process(strs.toCharArray(), 0);

    }

    /**
     * 在i位置之前的字符已经计算过了有多少个转换方法了，我现在不用关心了
     *
     * 只关心i位置之后能有多少中转换方法
     *
     * 如果i位置为0，则不能进行转换 直接返回0
     *
     * 如果i位置为1,或者2时，可以根据i+ 1位置的值有多种转换方式
     *
     *
     * 如果i位置为 3——9的话，那么就只能有一种转化方式
     *
     * 直到数组中所有的字符遍历完一次，才是一种转化结果
     */
    public int process(char[] chs, int i){

        if( i == chs.length){
            //遍历完一次，一次转化结果
            return 1;
        }

        if(chs[i] == '0'){
            return 0;
        }

        //如果i位置为1时，可以单独作为一个转化，也可以根据i+1位置一起做转化
        if(chs[i] == '1'){
            //单独转化，直接去 i+1位置做决定
            int res = process(chs,i+1);

            if(i + 1 < chs.length){
                //让i和i+1一起作为转化结果，直接去 i+2位置做决定
                res += process(chs, i+2);
            }
        }else if(chs[i] == '2'){
            int res = process(chs, i + 1);

            if(i + 1 < chs.length && chs[i+1] >= '0' && chs[i] <= '6'){
                res += process(chs, i + 2);
            }
        }

        //如果i位置时是 3 -9 ，则只可能有一种转化结果，直接去 i+1位置做决定
        return process(chs,i + 1);

    }


}
