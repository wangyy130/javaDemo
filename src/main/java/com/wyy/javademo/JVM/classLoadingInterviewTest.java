package com.wyy.javademo.JVM;

public class classLoadingInterviewTest {

    public static void main(String[] args) {
        System.out.println(T1.count);
    }

}


class T1 {
    public static int count = 2; //0
//    public static T t = new T1(); // null


    //private int m = 8;

    private T1() {
        System.out.println("调用构造方法了");
        count ++;
        //System.out.println("--" + count);
    }
}