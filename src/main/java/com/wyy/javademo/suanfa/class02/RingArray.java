package com.wyy.javademo.suanfa.class02;

/*
用数组实现栈和队列
 */
public class RingArray {

    public static class MyArrayQueue{

        private int[] array;
        private int putIndex;
        private int takeIndex;
        private int size;
        private int limit;


        MyArrayQueue(int limit){
            array = new int[limit];
            putIndex = 0;
            takeIndex = 0;
            size = 0;
            this.limit = limit;
        }

        //向队列中添加数据
        public void add(int data){

            if(size == limit){
                throw new RuntimeException("已经满了哦！");
            }

            array[putIndex] = data;
            size++;

            if(++putIndex == limit){
                putIndex = 0;
            }
        }


        //从队列中取数据
        public int pop(){
            if(size == 0){
                throw new RuntimeException("没有东西拿了哦！");
            }

            size--;

            int data = array[takeIndex];

            if(++takeIndex == limit){
                takeIndex = 0;
            }

            return data;

        }

    }
}
