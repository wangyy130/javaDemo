package com.wyy.javademo.suanfa.class08;

/**
 * 判断一棵二叉树是否为满二叉树
 *
 * 满二叉树满足条件
 *  2 ^ L -1 = N
 *
 *  L 为树的高度
 *  N 为节点数量
 */
public class IsFull {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }


    public class Info{
        public int L; //高度
        public int N ; //节点数目

        Info(int L, int N){
            this.L = L;
            this.N = N;
        }
    }

    public Info process(Node head){
        if(head == null){
            return new Info(0, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int L = 1;
        int N = 1;
        L = Math.max(leftInfo.L , rightInfo.L) + 1;
        N = rightInfo.N + leftInfo.N + 1;

        return new Info(L,N);
    }


    public boolean isFull(Node head){
        if(head == null){
            return true;
        }
        Info all = process(head);
        return (2 ^ all.L) - 1 == all.N;
    }
}
