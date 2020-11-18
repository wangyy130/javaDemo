//package com.wyy.javademo.Thread;
//
//import co.paralleluniverse.fibers.Fiber;
//import co.paralleluniverse.fibers.SuspendExecution;
//import co.paralleluniverse.strands.SuspendableRunnable;
//
//
//
////纤程的使用
//public class T09_Fiber {
//
//    public static void main(String[] args) throws  Exception {
//        long start = System.currentTimeMillis();
//
//
//        int size = 10000;
//
//        Fiber<Void>[] fibers = new Fiber[size];
//
//        for (int i = 0; i < fibers.length; i++) {
//            fibers[i] = new Fiber<Void>(new SuspendableRunnable() {
//                public void run() throws SuspendExecution, InterruptedException {
//                    calc();
//                }
//            });
//        }
//
//        for (Fiber<Void> voidFiber : fibers) {
//            voidFiber.start();
//        }
//
//        for (Fiber<Void> fiber : fibers) {
//            fiber.join();
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//
//
//    }
//
//    static void calc() {
//        int result = 0;
//        for (int m = 0; m < 10000; m++) {
//            for (int i = 0; i < 200; i++) result += i;
//
//        }
//    }
//}
