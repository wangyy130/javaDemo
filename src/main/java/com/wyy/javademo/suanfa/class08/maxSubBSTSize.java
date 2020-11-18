package com.wyy.javademo.suanfa.class08;

/**
 * 求一棵二叉树的最大子搜索二叉树的大小
 */
public class maxSubBSTSize {


    class Node{
        public  Node left;
        public Node right;
        public int value;
        Node(int v){
            value = v;
        }
    }


    //返回的信息，一棵树的最大值，最小值，是否为二叉搜索树，最大二叉搜索树的节点数
    class Info{
        public int max;
        public  int min;
        public boolean isAllBST;
        public int maxSubBSTSize;

        Info(int max,int min,boolean isAllBST,int maxSubBSTSize){
            this.max = max;
            this.min = min;
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }

    public int getMaxSubBSTSize(Node head){
        if(head == null){
            return 0;
        }
        return process(head).maxSubBSTSize;
    }



    public Info process(Node head){
        if(head == null){
            return new Info(0,0,true,0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);


        int max = 0;
        max = Math.max(Math.max(leftInfo.max,rightInfo.max),max);

        int min = 0;
        min = Math.min(Math.min(min,leftInfo.min),rightInfo.min);

        boolean isAllBST = false;
        int maxSubBSTSize = 0;

        int X = head.value;
        if(leftInfo.isAllBST && rightInfo.isAllBST && leftInfo.max < X && X < rightInfo.min){
            isAllBST = true;
            maxSubBSTSize = leftInfo.maxSubBSTSize + rightInfo.maxSubBSTSize + 1;
        } else if(leftInfo.isAllBST && rightInfo.isAllBST){
            maxSubBSTSize = Math.max(Math.max(maxSubBSTSize,leftInfo.maxSubBSTSize),rightInfo.maxSubBSTSize);
        }else if(leftInfo.isAllBST){
            maxSubBSTSize = Math.max(maxSubBSTSize,leftInfo.maxSubBSTSize);
        }else if(rightInfo.isAllBST){
            maxSubBSTSize = Math.max(maxSubBSTSize,rightInfo.maxSubBSTSize);
        }

        return new Info(max,min,isAllBST,maxSubBSTSize);



    }
}
