package com.wyy.javademo.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T07_ReadWriteLock {

    public static ReentrantLock lock = new ReentrantLock();

    public static int value;


    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();




    public static void read(Lock lock) {
        writeLock.lock();
        readLock.lock();
        readLock.unlock();
        writeLock.unlock();
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
            //模拟读取操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
            //模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

//        Runnable readR =  () -> read(lock);
        Runnable readR = () -> read(readLock);


//        Runnable writeR = () -> write(lock,new Random().nextInt());

//        Runnable writeR = () -> write(writeLock,new Random().nextInt());

        for(int i=0; i<1; i++) new Thread(readR).start();
//        for(int i=0; i<2; i++) new Thread(writeR).start();

    }

}
