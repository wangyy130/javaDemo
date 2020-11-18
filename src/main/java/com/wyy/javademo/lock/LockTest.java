package com.wyy.javademo.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    ReentrantLock reentrantLock = new ReentrantLock();
    ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    CountDownLatch countDownLatch = new CountDownLatch(100);

    public  void lock() throws InterruptedException {
        reentrantLock.lock();
        reentrantLock.unlock();
        lock.readLock();
        lock.writeLock();

        countDownLatch.countDown();
        countDownLatch.await();
        countDownLatch.getCount();
    }

    public boolean is(int a){
        for(;;){
            System.out.println("");
        }

    }

}
