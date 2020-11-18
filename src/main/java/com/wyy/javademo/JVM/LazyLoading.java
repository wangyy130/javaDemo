package com.wyy.javademo.JVM;


/**
 * 懒加载
 *
 * 加载final 声明的静态变量不需要初始化
 */

public class LazyLoading { //严格讲应该叫lazy initialzing，因为java虚拟机规范并没有严格规定什么时候必须loading,但严格规定了什么时候initialzing
    public static void main(String[] args) throws Exception {
//        P p;
//        X x = new X();
//        System.out.println(P.i);
        System.out.println(P.j);
//        Class.forName("com.wyy.javademo.JVM.LazyLoading$X");

    }

    public static class P {
        final static int i = 8;
        static int j = 9;
        static {
            System.out.println("P");
        }

        P(){
            System.out.println("init P");
        }
    }

    public static class X extends P {
        static {
            System.out.println("X");
        }

        X(){
            System.out.println("init X");
        }
    }
}
