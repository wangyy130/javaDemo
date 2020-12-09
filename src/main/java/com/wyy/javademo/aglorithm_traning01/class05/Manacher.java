package com.wyy.javademo.aglorithm_traning01.class05;

/*
    manacher算法，解决最长回文子序列的问题
    给定回文区域的左边界L 和 右边界R
    遍历字符串的每个字符i,
    分为两个大情况：(R为最后一个符合回文的字符的位置)
        1、i在R 外，暴力遍历
        2、i在R内，可以进行优化
            a、i的对称点i'在L..R内，则i位置的回文半径 = i'
            b、i'在L之外，则 i 位置回文半径 = R - i
            c、i' 左边界和L压线，则 i 需要从R位置开始继续暴力扩


       注：代码中R 为第一个不符合回文字符的位置
 */
public class Manacher {

    //返回字符串中回文子串的最大长度 = pArr[] 中的最大值 - 1
    public static int manacher(String s){
        if(s == null || s.length() == 0){
            return 0;
        }

        //拼接带 '#' 的字符数组
        char[] str = getManacherArray(s);
        int R = -1; //当前最大回文子串的最大右边界
        int C = -1; //最大右边界对应的额中心C点位置
        int[] pArr = new int[str.length]; //存放每个字符的最大回文半径

        int max = Integer.MIN_VALUE;
        //上面所说的四种情况，可以按情况一一分类，同时这四种情况也可以进行优化合并
        for(int i = 0; i < str.length; i++){
            //合并情况 1,2,3,4 pArr[i] 至少会如下的表达式的值
            pArr[i] = R > i ?  Math.min(R - i, pArr[C * 2 - i]) : 1;

            //开始暴力扩
            while(i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }

            //每次遍历完一个位置，要判断R和C是否需要更新
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }

        return max - 1;
    }


    public static char[] getManacherArray(String s){
        char[] str = s.toCharArray();
        char[] res = new char[str.length * 2 + 1];
        int index = 0;
        for(int i = 0; i < res.length; i++){
            res[i] = (i & 1) == 0 ? '#' : str[index++];
        }

        return res;
    }


    public static void main(String[] args) {
        String s = "12321a12321hhhh";
        System.out.println(manacher(s));
    }
}
