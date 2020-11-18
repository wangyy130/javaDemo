package com.wyy.javademo.JVM;

public class StackTest {

    public static void main(String[] args) {
        //bipush 8 将8压入操作数栈中
        //istore_1 将栈顶的元素出栈，并存放入局部变量表中第1个的位置7`
        int i = 127;
        // iload_1 将局部变量表1位置的数取出来压栈，栈中的i = 8
        // iinc 1 by 1 将局部变量表中1位置的数加1，此时局部变量表i = 9
        // istore_1 将栈顶的元素出栈，并存放入局部变量表中1位置，i = 8
        i = i++;


        /**
         * bjpush 8
         * istore_1
         *
         * iinc 1 by 1  将局部变量表中的1位置的数 加1
         * iload_1 将局部变量表中1位置的数压入栈
         * istore_1 将栈顶的元素出栈并存入局部变量表中
         *
         */
//        i = ++i;

        //return
        System.out.println(i);
    }
}
