package com.wyy.javademo.suanfa.class08;

/**
 * 最低公共祖先问题给定一个头节点head 和两个节点 o1 和 o2
 * 1、利用map和set方法
 *  先遍历 o1，将树中所有节点和其父节点放入map中
 *
 * 这里有一个二叉树中求每个节点的父节点问题，可以通过map来递归获取
 *
 * 2、递归方法
 */
public class LowestAncestor {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public class Info{
        public boolean findO1; //是否找到了o1节点
        public boolean findo2; //是否找到了o2 节点

        public Node ans;

        public Info(boolean findO1,boolean findo2,Node ans){
            this.findO1 = findO1;
            this.findo2 = findo2;
            this.ans = ans;
        }
    }

    //递归方式求最低公共祖先
    public Info process(Node X,Node o1,Node o2){
        if(X == null){
            return new Info(false,false,null);
        }

        Info leftInfo = process(X.left, o1, o2);
        Info rightInfo = process(X.right, o1, o2);

        //是否已经发现了o1
        boolean findo1 = leftInfo.findO1 || rightInfo.findO1 || X == o1;
        //是否已经发现了o2
        boolean findo2 = leftInfo.findo2 || rightInfo.findo2 || X == o2;

        Node ans = null;

        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        }

        if(rightInfo.ans != null){
            ans = rightInfo.ans;
        }


        if(ans == null && findo1 && findo2){
            ans = X;
        }

        return new Info(findo1,findo2,ans);
    }


    public Node lowestAncestor(Node head,Node o1,Node o2){
        if(head == null){
            return null;
        }

        Info all = process(head, o1, o2);
        return all.ans;
    }


}
