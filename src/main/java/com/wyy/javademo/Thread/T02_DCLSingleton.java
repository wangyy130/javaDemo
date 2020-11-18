package com.wyy.javademo.Thread;

/**
 * 单例
 */
public class T02_DCLSingleton {

    private static volatile T02_DCLSingleton INSTANCE;

    private T02_DCLSingleton(){};


    public static T02_DCLSingleton getINSTANCE(){

        if(INSTANCE==null){
            synchronized (T02_DCLSingleton.class){
                if(INSTANCE==null){
                    INSTANCE=new T02_DCLSingleton();
                }
            }
        }
        return INSTANCE;
    }


    public static void main(String[] args) {
        getINSTANCE();
    }

}
