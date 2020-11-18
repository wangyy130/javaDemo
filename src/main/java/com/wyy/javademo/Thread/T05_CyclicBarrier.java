package com.wyy.javademo.Thread;

import org.springframework.core.annotation.SynthesizedAnnotation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T05_CyclicBarrier {


    static class TaskThread extends Thread{

        CyclicBarrier cyclicBarrier;

        TaskThread(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 处理完任务 A");
                cyclicBarrier.await();
                Thread.sleep(1000);
                System.out.println(getName() +" 处理完任务 B");
                cyclicBarrier.await();
                Thread.sleep(1000);
                System.out.println(getName() + " 处理完任务 C");
                cyclicBarrier.await();


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("任务完成");
            }
        });

        for(int i = 0 ;i < 5; i++){
            new TaskThread(cyclicBarrier).start();
        }

    }
}
