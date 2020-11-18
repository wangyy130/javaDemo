package com.wyy.javademo.JVM;

import com.wyy.javademo.DemoClass;

public class LoadClassTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = DemoClass.class.getClassLoader().loadClass("com.wyy.javademo.ChildInterface");
        System.out.println(clazz.getName());
    }
}
