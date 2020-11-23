package com.wyy.javademo.suanfa.class12;

/**
 * 两个字符串的最长公共子序列的问题
 *
 * str1,str2
 *
 * dp[i][j] str1 0-i 位置上
 * 和str2 0 - j 位置上最长公共子序列
 *
 * 四种可能性
 * 是否和 i，或者 j位置的字符相关
 *
 *
 */
public class CommonSubSequence {

    public int longestCommonSubSequence(String str1,String str2){
        if(str1 == null || "".equals(str1) || str2 == null || "".equals(str2)){
            return 0;
        }
        char[] char1 = str1.toCharArray(); //作为列 j
        char[] char2 = str2.toCharArray(); //作为行 i

        int[][] dp = new int[char1.length][char2.length];

        dp[0][0] = char1[0] == char2[0] ? 1 : 0;
        //填充第0列
        for(int i = 1; i < char1.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0],char1[i] == char2[0] ? 1 :0)  ;
        }
        //填充第0行
        for(int j = 1; j< char2.length; j++){
            dp[0][j] = Math.max(dp[0][j - 1], char2[j] == char1[0] ? 1 : 0);
        }

        //填充中间的值
        for(int i = 1; i < char1.length; i++){
            for(int j = 1; j < char2.length; j++){
                //第2种可能性和第三种选出最大的
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                if(char1[i] == char2[j]){
                    //第四种可能性
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }

                //第一种可能性不需要比对，因为第二种和第三种可能性已经比对过了
                //每种可能性的结果都跟他的上边，左对角，和左边三个格子相关
                //而第二种和第三种可能性分别是上边和左边，都会跟第一种可能性进行角逐，选最大的
            }
        }

        return dp[char1.length -1][char2.length - 1];

    }
}
