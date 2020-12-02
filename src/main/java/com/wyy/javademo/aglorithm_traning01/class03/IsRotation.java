package com.wyy.javademo.aglorithm_traning01.class03;


/*
    给定两个字符串str1，str2
    判断两个字符串是否互为旋转词
    123456的旋转词有
    234561
    345612
    456123
    561234
    612345


    如果两个数互为旋转词，那么 123456123456 中一定包含自己的旋转词子串
 */
public class IsRotation {
    /*
        是否互为旋转词
     */
    public static boolean isRotation(String a,String b){
        if(a == null || b == null || a.length() != b.length()){
            return false;
        }

        String c = a + a;

        return getIndexOf(c, b) != -1;
    }

    /**
     * 判断字符串str中是否包含了字符串match
     * @param str
     * @param match
     * @return
     */
    public static int getIndexOf(String str,String match){
        if(str == null || match == null || match.length() == 0 || str.length() < match.length()){
            return -1;
        }

        char[] strArr = str.toCharArray();
        char[] m = match.toCharArray();

        int[] next = getNextArray(m);

        int x = 0;
        int y = 0;

        while (x < strArr.length  && y < m.length){
            if(strArr[x] == m[y]){
                x++;
                y++;
            }else if(next[y] != -1){
                y = next[y];
            }else{
                x++;
            }
        }

        return y == m.length ? x - y : -1;
    }

    /**
     * 计算m字符数组中对应每个字符的前缀 = 后缀的最大长度
     * @param m
     * @return
     */
    public static int[] getNextArray(char[] m){
        if(m.length == 1){
            return new int[]{-1};
        }

        int[] next = new int[m.length];

        next[0] = -1;
        next[1] = 0;
        int cn = 0; //当前计算i位置时，要比对的位置

        //从第二个位置开始填充next数组
        int i = 2;

        while (i < next.length){
            if(m[cn] == m[i - 1]){
                next[i++] = ++cn;
            }else if(next[cn] != -1){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }

        return next;

    }


    public static void main(String[] args) {
        String a = "1234567";
        String b = "2345671";
        System.out.println(isRotation(a,b));
    }
}
