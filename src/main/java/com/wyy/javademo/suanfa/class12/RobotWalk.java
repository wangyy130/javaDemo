package com.wyy.javademo.suanfa.class12;

/**
 *  机器人行走问题
 *
 *  机器人行走，在1位置时，只能向右走到2，在N位置时，只能向左走到 N - 1
 *  在中间位置时，既可以向左走，也可以向右走
 *
 *  问给定 N个位置，初始位置在 M, 经过K步，走到P位置，一共有多少种走法
 */
public class RobotWalk {
    public int ways1(int M , int K, int P, int N){
        if(M < 1 || M > N || K < 1 || P < 1 || P > N || N < 2){
            return 0;
        }

        return  walk(N,P,K,M);
    }


    /**
     *
     * @param N 一共多少个位置
     * @param P 想要达到的位置
     * @param rest 剩余多少步
     * @param cur 当前来到的位置
     * @return
     */
    public int walk(int N, int P, int rest, int cur){
        //如果剩余步数为0，并且当前位置来到了P位置，说明当前的方法有效
        if(rest == 0){
            return  cur == P ? 1 : 0;
        }


        if(cur == 0){
            return walk(N, P , rest - 1, 1);
        }

        if(cur == N){
            return walk(N , P, rest - 1, N - 1);
        }

        return walk(N, P ,rest - 1, cur - 1) +
                walk(N ,P , rest - 1, cur + 1);
    }


    /**
     * 很多暴力递归都存在很多的重复计算，
     * 如果将中间的重复计算的结果保存在一个缓存中，每次需要时，直接从缓存中获取
     *
     * 这样的算法称为动态规划
     *
     *
     * 暴力递归不保存中间的结果，动态规划会保存中间的结果
     */

    //利用缓存的算法
    public int waysCache(int N, int M ,int P, int K){
        if(M < 1 || M > N || K < 1 || P < 1 || P > N || N < 2){
            return 0;
        }

        int dp[][] = new int[N + 1 ][K + 1];
        for(int i = 0; i < N ; i++){
            for(int j = 0; j < K; j++){
                dp[i][j] = -1;
            }
        }
        return walkCache(N, M, P, K , dp);
    }

    //将暴力递归的中间结果记录进dp缓存中
    public int walkCache(int N, int cur, int P ,int rest , int[][] dp){
        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }

        if(rest == 0){
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }


        if(cur == 0){
            dp[cur][rest] = walkCache(N, 1, P,rest - 1, dp );
            return dp[cur][rest];
        }

        if(cur == N){
            dp[cur][rest] = walkCache(N, N - 1, P,rest - 1, dp );
            return dp[cur][rest];
        }

        dp[cur][rest] =  walkCache(N, cur - 1, P,rest - 1, dp )
                        + walkCache(N, cur + 1, P,rest - 1, dp);

        return dp[cur][rest];
    }








}
