package com.wyy.javademo.Thread;



import java.util.concurrent.*;

public class ExecutorTest {



    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("name = " + Thread.currentThread().getName());
            }
        };

        //核心线程数为5，最大线程数为10
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,
//                5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));

//        ThreadPoolExecutor threadPoolExecutor2 = (ThreadPoolExecutor) Executors.newScheduledThreadPool(3);
//        threadPoolExecutor.execute(runnable);


//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable); //10
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable); // 18
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//
//
//
//        Thread.sleep(3000);
//
//        System.out.println("核心线程数 = " + threadPoolExecutor.getCorePoolSize());
//        System.out.println("当前线程池线程数 = "+threadPoolExecutor.getPoolSize());
//        System.out.println("maxPoolNum = " + threadPoolExecutor.getMaximumPoolSize());
//        System.out.println("当前队列中的任务数 queue size = " + threadPoolExecutor.getQueue().size());


        System.out.println("CPU = " + Runtime.getRuntime().availableProcessors());


//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable); //10
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable);
//
//
//        threadPoolExecutor.execute(runnable);
//        threadPoolExecutor.execute(runnable); //20
//        System.out.println("核心线程数 = " + threadPoolExecutor.getCorePoolSize());
//        System.out.println("线程池线程数 = "+threadPoolExecutor.getPoolSize());
//        System.out.println("maxPoolNum = " + threadPoolExecutor.getMaximumPoolSize());
//        System.out.println("queue size = " + threadPoolExecutor.getQueue().size());
//


//        Thread.sleep(10000);
////        System.out.println("核心线程数 = " + threadPoolExecutor.getCorePoolSize());
////        System.out.println("线程池线程数 = "+threadPoolExecutor.getPoolSize());
////        System.out.println("maxPoolNum = " + threadPoolExecutor.getMaximumPoolSize());
////        System.out.println("queue size = " + threadPoolExecutor.getQueue().size());
////        threadPoolExecutor.execute(runnable);
////        threadPoolExecutor.execute(runnable);
////
////        Thread.sleep(2000);


    }
}
