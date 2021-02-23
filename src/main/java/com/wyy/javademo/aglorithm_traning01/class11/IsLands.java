package com.wyy.javademo.aglorithm_traning01.class11;

/*
    岛问题
    给定一个矩阵，矩阵中每个元素不是0就是1
    我们认为上下左右的1可以连一起的为一块陆地，问矩阵中有几个陆地

    1、单cpu 感染法
    2、多cpu 并查集法
 */
public class IsLands {

    public static int isLands1(int[][] m){
        if(m == null || m[0] == null){
            return 0;
        }

        int M = m.length;
        int N = m[0].length;
        int res = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(m[i][j] == 1){
                    res++;
                    infect(m,i,j,M,N);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] m, int i, int j, int M, int N){
        if(i < 0 || i > M || j < 0 || j > M || m[i][j] != 1){
            m[i][j] = 2;

            infect(m, i + 1, j, N, M);
            infect(m, i - 1, j, N, M);
            infect(m, i, j + 1, N, M);
            infect(m, i, j - 1, N, M);
        }
    }
}
