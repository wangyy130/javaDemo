package com.wyy.javademo.suanfa.class11;


import java.sql.Statement;
import java.util.Stack;

/**
 * 利用递归来逆序栈
 */
public class ReverseStackUsingRecursive {

    /**
     *每次f()递归将栈中的最底层的数字last取出来进行记录为i ,将 其他 数字按原来顺序push进stack中
     * 每次reverse递归都将依次获取最底层的数字，并将i按顺序获取的最底层的数再依次压入栈
     */
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }

        Integer i = f(stack);
        reverse(stack);
        stack.push(i);
    }


    public static  Integer f(Stack<Integer> stack){
        Integer result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = f(stack); //获取最底层的数字
            stack.push(result); //将其余数字按原有顺序压入栈
            return last; //返回最底层的数字
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }















    //先将栈中最底层的那个数字返回，其余数字按照原本的顺序再依次压入栈
    public int f2(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            //last 弹出下一个数字，或者最后一个
            int last = f2(stack);
            stack.push(result);
            return last;
        }
    }



    public void reverse2(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }

        int i = f(stack);
        reverse2(stack);
        stack.push(i);

    }





}
