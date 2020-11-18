package com.wyy.javademo.suanfa.class08;

/**
 * 返回整棵二叉树的最大距离
 *
 * 分析最大距离判定条件如果给定节点x
 *
 * 1、结果和x无关
 *  左子树的最大距离 或者 右子树的最大距离
 * 2、结果和x有关
 *
 * 左子树的高度 + 右子树的最大高度 + 1
 *
 */
public class MaxDistance {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }


    public class Info{
        public int height;

        public int maxDistance;

        public Info(int height, int maxDistance){
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }


    /*
        给定头节点，求这棵树最大距离
     */
    public Info getMaxDistance(Node head){
        if(head == null){
            return new Info(0, 0);
        }

        Info leftInfo = getMaxDistance(head.left);
        Info rightInfo = getMaxDistance(head.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;

        int maxDistance = Math.max(leftInfo.maxDistance,rightInfo.maxDistance);
        maxDistance = Math.max(maxDistance,height);
        return new Info(height,maxDistance);
    }

}
