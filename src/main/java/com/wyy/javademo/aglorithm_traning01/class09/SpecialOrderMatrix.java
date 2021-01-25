package com.wyy.javademo.aglorithm_traning01.class09;

/*
    转圈打印矩阵
    1、将矩阵分为四部分
        分开打印
    2、给定一个起始点的行列 a,b
        给定一个结束点的行列 c,d
 */
public class SpecialOrderMatrix {

    public static void specialOrder(int[][] matrix){
        int a = 0; //起始点的行
        int b = 0; //起始点的列
        int c = matrix.length - 1; //结束点的行
        int d = matrix[0].length - 1; //结束点的列

        while (a <= c && b <= d){
            printEdge(matrix,a++, b++, c--, d--);
        }
    }


    public static void printEdge(int[][] m ,int a, int b, int c, int d){
        if(a == c){ //同行
            for(int i = b; i < d; i++){
                System.out.println(m[a][i] + " ");
            }
        }else if(b == d){ //同列
            for(int i = a; i < c; i++){
                System.out.println(m[i][b] + " ");
            }
        }else {
            int curR = a; //当前行
            int curC = b; //当前列

            while(curC != d){
                System.out.println(m[a][curC++] + " ");
            }

            while (curR != c){
                System.out.println(m[curR++][d] + " ");
            }

            while (curC != b){
                System.out.println(m[c][curC--] + " ");
            }

            while (curR != a){
                System.out.println(m[c--][a] + " ");
            }
        }
    }
}
