package com.wyy.javademo.aglorithm_traning01.class03;

/**
 * KMP算法
 * 给定一个字符串str，求str中是否包含子串match，如果包含，返回起始位置
 * 要求O(N)
 *
 * 本质是对暴力解法的一个优化
 */
public class KMP {

    public static int getIndexOf(String str, String match){
        if(str == null || match == null || match.length() == 0 || str.length() < match.length()){
            return -1;
        }

        char[] strs = str.toCharArray();
        char[] m = match.toCharArray();
        int x = 0; //遍历str的 i位置
        int y = 0; //遍历match的 i 位置
        int[] next = getNextArray(m);
        while (x < strs.length && y < m.length){
            //如果遍历位置字符相等，那么继续下一个比对
            if(strs[x] == m[y]){
                x++;
                y++;
            }else if(next[y] != -1){ //不相等时，y要回退，先判断回退的位置是不是 0位置
                y = next[y];
            }else{
                //如果是0位置，则说明 在x之前的位置都匹配不上了，去x之后的位置进行比对吧
                x++;
            }
        }

        //跳出循环时，要判断y是否越界，如果越界了，说明str中包含了match，否则不包含
        return y == m.length ? x - y : -1;
    }

    /**
     * 生成对应match字符串的一个前缀和后缀相等的最大长度的数组
     * @param m match字符串的字符数组
     * @return 返回生成的数组
     */
    public static int[] getNextArray(char[] m){
        if(m.length == 1){
            return new int[]{ -1 };
        }

        int[] next = new int[m.length];
        //人为规定 0 位置为 -1, 1位置为0
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        //cn 是需要跟当前 i-1 位置的字符进行比较的位置，
        //如果cn位置与 i -1匹配不上时，cn也是下一步需要跳转位置在next数组中的index
        int cn = 0;
        //从i= 2 位置开始填next数组
        while (i < next.length){
            if(m[i - 1] == m[cn]){
                //配对成功 接下来 i++
                // 配对成功 i位置的前缀=后缀的长度 就是 cn + 1;
                //配对成功后， 接下来i+ 1 位置要判断的cn 就是 cn + 1
                next[i++] = ++cn;
            }else if(next[cn] != -1){
                //如果比对没成功，cn要调回到当前cn位置的字符 在match中前缀 = 后缀长度的位置
                cn = next[cn];
            }else{
                //没有比对成功，且前面没有位置了
                next[i++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String s = "kkkabaabacabaabasaa";
        String m = "abaabas";
        System.out.println(getIndexOf(s,m));
    }
}
