package com.wyy.javademo.aglorithm_traning01.class09;

/**
 * 矩阵的原地旋转的遍历
 * 要求必须是正方形
 * 1、将正方形边分为N (N为边长-1) 个小组
 * 每个小组有四个元素，每个元素都可以通过表达式来表达a,b,c,d
 * 2、每次按小组中的元素一次旋转遍历,通过临时变量进行替换
 */
public class RotateMatrix {

    public static void rotate(int[][] matrix){
        int a = 0; //矩阵起始点所在的行
        int b = 0; //矩阵起始点所在的列
        int c = matrix.length - 1; //矩阵右下角的点所在的行
        int d = matrix[0].length - 1; //矩阵右下角的点所在的列

        while(a < c){
            rotateEdge(matrix,a++, b++, c--,d--);
        }
    }

    public static void rotateEdge(int[][] m, int a, int b, int c, int d){

        int tmp = 0;
        //i 代表小组号
        for(int i = 0; i < d - b; i++){
            tmp = m[a][b + i] ; //每个小组的第一个元素
            m[a][b + i] = m[c - i][b] ; //将第四个数赋值给第一个
            m[c - i][b] = m[c][d - i] ; //将第三个赋值给第四个
            m[c][d - i] = m[a + i][d] ; //将第二个赋值给第三个
            m[a + i][d] = tmp;

        }
    }
}
