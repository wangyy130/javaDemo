package com.wyy.javademo.Thread;

/**
 * 线程可见性
 */
public class T01_threadVisible {

//    private static boolean running=true;
 private static volatile boolean running=true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (running){
                System.out.println("======");
            }
            System.out.printf("end");
        },"thread-visible").start();

        Thread.sleep(1000);

//        new Thread(()->{
//
//
//        }).start();
        running=false;
        System.out.println("main1");
        Thread.sleep(1000);

        System.out.println("main2");

    }
}
