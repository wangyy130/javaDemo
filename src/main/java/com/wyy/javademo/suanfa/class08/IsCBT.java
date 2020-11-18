package com.wyy.javademo.suanfa.class08;

import java.util.LinkedList;

/**
 * 判断是否为完全二叉树
 *
 * 1、通过层级遍历
 * 当出现叶子节点时，之后的节点必须全部为叶子节点
 * 如果一个节点只有右节点没有左节点，一定不是完全二叉树
 *
 * 2、递归套路实现
 * 几种可能的情况：
 * a/ 左树是满的，右树是完全二叉树 高度相等
 * b/左树是满，右树也是满的 高度相等
 * c/左树是完全二叉树，右树是满的 高度左比右边高1
 * d/左满的，右边也是满的，但是左边高度高1
 */
public class IsCBT {


    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    //层级遍历法
    public boolean levelIsCBT(Node head){
        if(head == null){
            return true;
        }

        LinkedList<Node> list = new LinkedList<>();
        list.add(head);
        Node left = null;
        Node right = null;

        //是否遇到过两个孩子不全的情况
        boolean leaf = false;

        while (!list.isEmpty()){
            Node cur = list.poll();
            left = cur.left;
            right = cur.right;

            if(
                    //如果之前遇到过两个孩子不全的情况，并且 这次 左孩子和右孩子不全是null，也不是完全二叉树
                    (leaf && !(left != null || right != null))
                     ||
                            //如果有右孩子，没有左孩子，肯定不是完全二叉树
                     (left == null && right != null)){
                return false;
            }

            if(left != null){
                list.add(left);
            }

            if(right != null){
                list.add(right);
            }

            if(left == null || right == null){
                leaf = true;
            }

        }

        return true;
    }


    public static class Info{
        public boolean isFull;
        public boolean isCBT;

        public int height;

        Info(boolean isFull,boolean isCBT ,int height){
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }



    public Info process(Node head){
        if(head == null){
            return new Info(true, true, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;

        boolean isCBT = false;


        if(isFull){
            isCBT = true;
        }else{
            if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
                isCBT = true;
            }

            if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                isCBT = true;
            }

            if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
                isCBT = true;
            }
        }

        return new Info(isFull, isCBT, height);
    }



    public boolean isCBT2(Node head){
        if(head == null){
            return true;
        }

        Info all = process(head);
        return all.isCBT;

    }

}
