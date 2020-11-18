package com.wyy.javademo.suanfa.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树的层级遍历
 */
public class LevelTraversalTree {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }


    //层级遍历,利用队列，按层次，从左向右依次加入队列，并弹出
    //直到父节点的层数，每次加当前节点的时候，层数+1
    public void level(Node head){

        if(head == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            System.out.print(cur.value);

            if(cur.left != null){
                queue.add(cur.left);

            }

            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }


    /*
        求二叉树中节点最多的层

        利用map来记录每个节点的层级，结合队列进行层次遍历
     */
    public int getMaxNodesByMap(Node head){
        if( head == null){
            return 0;
        }

        int max = 0;
        int curLevel = 0;
        int curLevelNodes = 0;
        Map<Node,Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        map.put(head,1);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int level = map.get(cur);

            if(cur.left != null){
                map.put(cur.left, level + 1);
                queue.add(cur.left);
            }

            if(cur.right != null){
                map.put(cur.right, level + 1);
                queue.add(cur.right);
            }

            if(level == curLevel){
                //如果还在当前层，节点数+1
                curLevelNodes++;
            }else{
                //新的一层开始的时候，就更新之前层中的max
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1; //新的一层节点数直接设置为1
                curLevel++; // 下一层
            }

        }

        return Math.max(max,curLevelNodes);

    }


    /*
        不利用map的方式来求二叉树的最大宽度

        利用层级遍历，记录每一层的结束节点
        利用队列，每次弹出一个节点，将下一层子节点加入队列中，并记录结束节点

        如果弹出的节点不是结束节点，节点数+1，如果是结束节点，则与max进行比较，并且开始下一层的初始化条件
     */
    public int maxWidthNoMap(Node head){

        if(head == null){
            return  0;
        }

        Node curEnd = head; //记录当前层的结束节点
        Node nextEnd = null; //记录下一层的结束节点

        int max = 0 ;
        int curLevelNodes = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }

            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }

            curLevelNodes++;
            if(cur  == curEnd){
                max = Math.max(max, curLevelNodes);
                curEnd = nextEnd;
                curLevelNodes = 0; //这里和利用map的初始化不同
            }
        }

        return Math.max(max, curLevelNodes);

    }


}
