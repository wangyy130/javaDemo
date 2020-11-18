package com.wyy.javademo.Thread;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Semaphore;

public class T08_Semaphore {



    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);



        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 starting ..........");
                Thread.sleep(1000);
                System.out.println("T1 ending ........");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println("T1 release..........");
            }



        }).start();


        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 starting ..........");
                Thread.sleep(1000);
                System.out.println("T2 ending ........");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println("T2 release..........");
            }


        }).start();


//        System.out.println((1<<16) + 1);
//        System.out.println((65536 * 2) >>> 16);
        BigDecimal transferRate = new BigDecimal(11 * 100).divide(new BigDecimal(44), 1,RoundingMode.HALF_UP);

        System.out.println(transferRate);
//        System.out.println(65537 >>> 16);

    }
}
