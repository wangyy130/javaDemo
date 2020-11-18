package com.wyy.javademo.JVM;

public class StringTest {

    public static void main(String[] args) {
        String s1 = new String("abc");
        System.out.println("s1 hashcode =" + s1.hashCode());
        s1.intern();
//        System.out.println("s2 hashcode1 =" + s2.hashCode());

        String s3 = "abc";
        System.out.println("s3 hashcode1 =" + s3.hashCode());

        String s4 = s3.intern();

        String s5 = new String("a")+new String("bc");
        System.out.println("s5 hashcode =" + s5.hashCode());

        s5.intern();

        System.out.println("s5 hashcode1 =" + s5.hashCode());


        String s7 = "a" + "bc";
        System.out.println(s1 == s3);
    }
}
