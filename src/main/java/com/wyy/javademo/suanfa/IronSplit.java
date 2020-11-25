package com.wyy.javademo.suanfa;

public class IronSplit {

    public static  int cut(int[] p, int n){
        if(n == 0){
            return 0;
        }

        int p1 = p[n - 1];
        int p2 = Integer.MIN_VALUE;

        for(int i = 1; i < n ; i ++){
            p2 = Math.max(p[n - i - 1] + cut(p,  i), p2);
        }

        return Math.max(p1, p2);
    }


    public static void main(String[] args) {
        int[] p = new int[]{1,5,8,9,10,17,17,20,24,30,27};
        System.out.println(cut(p,11));
        System.out.println(dpWay(p,11));
    }




    public static int  dpWay(int[] p, int n){
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = p[0];
        int p2 = Integer.MIN_VALUE;
        for(int i = 2; i <= n; i++ ){
            int p1 = p[i - 1];

            for(int j = 1 ; j < i && i - j >= 0; j++){
                p2 = Math.max(dp[j] + dp[i - j], p1);
            }

            dp[i] = Math.max(p1, p2);
//            System.out.println(i + " = " +dp[i]);
        }

        return dp[n];

    }
}
