package com.wyy.javademo.suanfa.class02;

import java.util.Stack;

/**
 * 用两个栈结构实现队列结构
 */
public class TwoStackImplQueue<T> {
    public static class MyQueue<T>{
        public Stack<T> dataStack;
        public Stack<T> helpStack;

        MyQueue(){
            dataStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void add(T data){

        }

    }
}
