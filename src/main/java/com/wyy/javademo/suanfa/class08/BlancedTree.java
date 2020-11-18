package com.wyy.javademo.suanfa.class08;

import com.wyy.javademo.suanfa.class07.BinaryRecursion;

/**
 * 判断一棵二叉树是否为平衡二叉树
 *
 * 平衡二叉树： 二叉树中每一棵子树的左子树和右子树高度相差不能超过1，自己本身左子树和右子树高度相差也不能超过1
 *
 */
public class BlancedTree {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    //平衡二叉树判断需要提供的信息
    public class Info{
        public int height;
        public boolean isBalanced;
        public Info(int height,boolean isBalanced){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }



    /*
        判断一棵二叉树是否为平衡二叉树
     */
    public Info isBalanced(Node head){
        if(head == null){
            return new Info(0, true);
        }

        Info leftInfo = isBalanced(head.left);
        Info righInfo = isBalanced(head.right);

        int height = Math.max(leftInfo.height, righInfo.height) + 1;
        boolean isBalanced = false;

        if(leftInfo.isBalanced && righInfo.isBalanced
            && Math.abs(leftInfo.height - righInfo.height) < 2){
            isBalanced = true;
        }

        return new Info(height , isBalanced);

    }





}
