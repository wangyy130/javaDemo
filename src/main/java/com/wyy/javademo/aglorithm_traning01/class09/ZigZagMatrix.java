package com.wyy.javademo.aglorithm_traning01.class09;

/*
    zigzag打印
    给定两个变量 A，B
    A点行不变，列一直增加，直到不能增加时，行开始增加
    B点列不变，行一直增加，直到行不能增加时，列开始增加

    二者的轨迹有fromUp变量来控制

 */
public class ZigZagMatrix {

    public static  void zigZagPrint(int[][]matrix){
        int Ar = 0; //临时变量A点所在的行
        int Ac = 0; //A点所在的列
        int Br = 0; //B点所在的行
        int Bc = 0; //B点所在的列
        int EndR = matrix.length - 1; //最大行
        int EndC = matrix[0].length - 1; //最大列
        boolean fromUp = false;
        while (Ar != EndR + 1){
            printLevel(matrix,Ar,Ac,Br,Bc,fromUp);

            //遵循一个原则：先动的后更新，不能调换顺序
            Ar = Ac == EndC ? Ar + 1 : Ar;
            Ac = Ac == EndC ? Ac : Ac + 1;

            Bc = Br == EndR ? Bc + 1 : Bc;
            Br = Br == EndR ? Br : Br + 1;
            fromUp = !fromUp;
        }

    }


    public static void printLevel(int[][] m ,int Ar, int Ac, int Br, int Bc, boolean f){
        if(f){
            while (Ar != Br + 1){
                System.out.println(m[Ar++][Ac--] + " ");
            }
        }else{
            while ((Br != Ar - 1)){
                System.out.println(m[Br--][Bc++] + " ");
            }
        }
    }
}
