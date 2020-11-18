package com.wyy.javademo.suanfa.class02;

import java.util.Stack;

/*
实时获取当前栈中最小的值
 */
public class MinStack {

    public Stack<Integer> stackData;
    public Stack<Integer> stackMin;

    MinStack(){
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void pushData(Integer data){
        if(stackMin.isEmpty()){
            stackMin.push(data);
        }else if(stackMin.peek()>=data){
            stackMin.push(data);
        }else if(stackMin.peek() <=data){
            stackMin.push(stackMin.peek());
        }
        stackData.push(data);

    }

    public void pop(){
        if(stackData.isEmpty()){
            throw new RuntimeException("没有东西可以拿了");
        }
        stackData.pop();
        stackMin.pop();

    }


    public int getMin(){
        if(stackMin.isEmpty()){
            throw new RuntimeException("没有值");
        }

        return stackMin.peek();
    }

}
