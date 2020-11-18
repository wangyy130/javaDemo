package com.wyy.javademo.Thread;

import java.util.concurrent.locks.LockSupport;

public class T03_LockSupportTest {



    public static void main(String[] args) {

        Thread t = new Thread(()->{
           for(int i = 0 ; i < 10; i++){
               System.out.println(i);

               if( i == 5){
                   LockSupport.park();
               }

               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t.start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t);

    }
}
