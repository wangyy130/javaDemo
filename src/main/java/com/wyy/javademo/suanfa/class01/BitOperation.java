package com.wyy.javademo.suanfa.class01;

import java.util.Arrays;

/**
 * 位运算相关基础
 * 1、与 &  都为1时则为1，否则为0
 * 2、或 |  一个为1 ，则为1，否则为0
 * 3、异或 ^ 无进位相加 相同为0，不同为1
 * 4、取反 ~  1-0,0-1
 * 5、N & (~N+1) 取最右边的1
 */
public class BitOperation {

    /**
     * 位运算交换两个数的值
     *
     */
    public static  void swap(int a ,int b){
        a = a ^ b ;
        b = a ^ b ;
        a = a ^ b ;
        System.out.print("a=" + a);
        System.out.print("b=" + b);
    }


    /*
        取二进制数最右边的1的值
     */
    public static int right1(int num){
        return num & ((~num)+1);
    }


    /**
     * 取一个数组中唯一一个存在奇数个数的数，其他数都是偶数个
     *
     */
    public static int getOddNum(int[] array){
        int ret = 0;
        for(int i = 0; i < array.length; i++){
            System.out.println("i = "+ i);
            ret = ret ^ array[i];
        }
        return ret;
    }


    /*
        一个数组中，只有两种数出现了奇数次，其他都是偶数次，找出这两个数
     */
    public static  void getOddTimesNum(int[] array){
        int eor = 0;

        /*
        假设出现奇数的位a 和 b
        那么，数组中所有数异或完成异或，结果为 a^b
         */
        for(int i = 0; i < array.length; i++) {
            eor ^= array[i];
        }

        //既然a ！= b  那么必然有一位是不同的，一个为0，一个为1，找出最右边不同的一位
        int rightone = eor & (~eor + 1); // 提取出最右的1
        int onlyone=0;
        for(int i = 0; i < array.length ; i++){
            // 与运算是两个位置都为1时才为1，否则为0
            if((rightone & array[i]) != 0){
                onlyone ^= array[i];
            }
        }
        System.out.println("onlyone="+onlyone + "and anothor="+ (onlyone ^ eor));

    }

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // eor = a ^ b
        // eor != 0
        // eor必然有一个位置上是1
        // 0110010000
        // 0000010000
        int rightOne = eor & (~eor + 1); // 提取出最右的1
        int onlyOne = 0; // eor'
        for (int i = 0 ; i < arr.length;i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000

            System.out.println("i= "+ i + " array[i]= "+ arr[i]);
            System.out.println("rightone= " + rightOne);
            System.out.println("res= "+ (arr[i] & rightOne));
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];

            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }



    public static void main(String[] args) {
//        swap(5,6);
//
//        System.out.println("最右边1的值为num = "+right1(432));
//
//        int[] array = {3,3,5,5,5,6,6,5,7,5,7,3};
//
//        System.out.println("ret = " + getOddNum(array) );

        int[] array2 = {3,2,5,2,3,7};
//        getOddTimesNum(array2);

        printOddTimesNum2(array2);
    }

}
