package com.wyy.javademo.suanfa.class08;


/**
 * 判定一个二叉树是否为二叉搜索树
 *
 * 二叉搜索树：无重复值，根节点x，左子树都小于x的值，右子树都大于x的值
 */
public class IsBST {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }


    //判定二叉树的信息
    public class Info{
        public int max;
        public int min;
        public boolean isBST;

        Info(int max, int min , boolean isBST){
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }



    public Info process(Node head){

        //BaseCase 因为head为null所以最大值和最小值无法判定，可以留着下面去判空处理
        if(head == null){
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.value ;
        int min = head.value ;

        if(leftInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
        }

        if(rightInfo != null){
            max = Math.max(max,rightInfo.max);
            min = Math.min(min,rightInfo.min);
        }

        boolean isBST =  false;
        if (
                (leftInfo == null || (leftInfo.isBST && leftInfo.max < head.value)) &&
                        (rightInfo == null || (rightInfo.isBST && rightInfo.min > head.value))
        ) {

            isBST = true;
        }



        return new Info(max, min , isBST);
    }

}
