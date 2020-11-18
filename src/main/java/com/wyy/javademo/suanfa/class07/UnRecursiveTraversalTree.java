package com.wyy.javademo.suanfa.class07;

import java.util.Stack;

/**
 * 二叉树非递归遍历
 *
 * 主要借助于栈来实现
 */
public class UnRecursiveTraversalTree {
    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    //二叉树先序遍历 ，非递归方式 ,头，左，右
    // ====弹出就打印====
    public void pre(Node head){
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()){
            head = stack.pop();
            System.out.print(head.value);

            if(head.right != null){
                stack.push(head.right);
            }

            if(head.left != null){
                stack.push(head.left);
            }
        }

    }

    /*
        后序遍历，借助两个栈实现
        将先序遍历改成 头，右 ，左 后反转
     */
    public void pos1(Node head){
        if(head == null){
            return;
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(head);

        while (!s1.isEmpty()){
            head = s1.pop();
            s2.push(head);
            System.out.print(head.value);

            if(head.left != null){
                s1.push(head.left);
            }

            if(head.right != null){
                s1.push(head.right);
            }

        }

        while (!s2.isEmpty()){
            System.out.print(s2.pop() + " ");
        }

    }


    /*
        非递归 中序遍历 ，
        先一次将所有左子节点放入栈中
        弹出即打印

     */
    public void in(Node head){

        if(head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();

        while (head != null || !stack.isEmpty()){

            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                System.out.print(head.value);
                head = head.right;
            }
        }

    }

    /*
      后序遍历2，利用两个指针

      左，右，头
      h 每次打印后，h指向上次打印的节点

     */
    public  void pos2(Node h){

        if( h == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(h);

        Node c = null;
        while (!stack.isEmpty()){
            c = stack.peek();

            if(c.left != null && h != c.left && h != c.right){
                stack.push(c.left);
            }else if( c.right != null && h != c.right){
                stack.push(c.right);
            }else {
                System.out.print(stack.pop()+" ");
                h = c;
            }
        }
    }








    public void pos22(Node head){
        if(head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c = null;
        while (!stack.isEmpty()){
            c = stack.peek();

            if(c.left != null && c.left != head  && c.right != head){
                stack.push(c.left);
            }else if (c.right != null && c.right != head){
                stack.push(c.right);
            }else{
                head = stack.pop();
                System.out.print(head.value + " ");

            }
        }

    }




}
