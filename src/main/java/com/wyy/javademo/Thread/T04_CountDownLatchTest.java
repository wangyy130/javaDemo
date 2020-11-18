package com.wyy.javademo.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T04_CountDownLatchTest {

    static class MyList{
        MyList(){}
        List<Integer> l = new ArrayList<>();

        void add(Integer i){
            l.add(i);
        }

        int size(){
            return l.size();
        }
    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        MyList myList = new MyList();

        new Thread(()->{
            System.out.println("t1启动");
            for(int i = 0 ; i< 10; i++){

                if(myList.size() == 5){
                    countDownLatch.countDown();
                    try {
                        countDownLatch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                myList.add(i);
                System.out.println(i);
            }
            System.out.println("t1结束");

        }).start();


        new Thread(() -> {
            System.out.println("t2启动");

            if(myList.size() != 5){
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("t2结束");
            countDownLatch2.countDown();
        }).start();




    }
}
