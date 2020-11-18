package com.wyy.javademo.suanfa.class07;

/**
 * 二叉树递归遍历
 *
 * 1、先序，后序，中序
 * 都是基于递归序的，没进行一次递归序，都会经过一个节点3次，在经过第几次进行打印就是什么序
 */
public class BinaryRecursion {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    //递归序
    public void f(Node head){
        if(head == null){
            return;
        }
        // 先序
        f(head.left);
        //中序
        f(head.right);
        //后序
    }


}
