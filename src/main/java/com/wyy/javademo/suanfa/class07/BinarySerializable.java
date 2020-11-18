package com.wyy.javademo.suanfa.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * 主要利用递归序进行
 *
 * 当子树为null时，序列化时也需要记录
 *
 * 主要通过队列实现
 */
public class BinarySerializable {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    public Queue<Integer> preSerializable(Node head){
        Queue<Integer> queue = new LinkedList<>();
        pre(head,queue);
        return queue;
    }

    //先序 序列化
    public Queue<Integer> pre(Node head,Queue<Integer> queue){

        if(head == null){
            queue.add(null);
        }else{
            queue.add(head.value);
            pre(head.left,queue);
            pre(head.right,queue);
        }

        return queue;
    }



    public Node preb(Queue<Integer> queue){
        if(queue == null || queue.isEmpty()){
            return null;
        }

        return preBuild(queue);
    }


    //先序序列的反序列化
    public Node preBuild(Queue<Integer> queue){

        Integer value = queue.poll();
        if(value == null){
            return null;
        }

        Node head = new Node(value);
        head.left = preBuild(queue);
        head.right = preBuild(queue);

        return head;
    }




    /*
        层次遍历序列化
     */
    public Queue<Integer> levelSer(Node head){


        Queue<Integer> q2 = new LinkedList<>(); //用于序列化的队列

        if(head == null){
            q2.add(null);
        }else{
            Queue<Node> q1 = new LinkedList<>();
            q1.add(head);
            q2.add(head.value);

            while (!q1.isEmpty()) {
                Node cur = q1.poll();
                if(cur.left != null){
                    //如果不为null 既加队列，又序列化
                    q1.add(cur.left);
                    q2.add(cur.left.value);
                }else{
                    //如果为null，只序列化，不加队列
                    q2.add(null);
                }

                if(cur.right != null){
                    q1.add(cur.right);
                    q2.add(cur.right.value);
                }else{
                    q2.add(null);
                }

            }

        }

        return q2;
    }


    /**
     * 层次遍历反序列化
     *
     * 利用队列进行辅助
     */
    public Node buildByLevelQueue(Queue<Integer> list){
        if(list == null || list.isEmpty()){
            return null;
        }

        Node head = generateNode(list.poll());
        //遍历的队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        //遍历队列直到为空
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            cur.left = generateNode(list.poll());
            cur.right = generateNode(list.poll());

            if(cur.left != null){
                //如果不为空，加入遍历队列，否则不加
                queue.add(cur.left);
            }

            if(cur.right != null){
                queue.add(cur.right);
            }

        }

        return head;

    }



    public static Node generateNode(Integer val) {
        if (val == null) {
            return null;
        }
        return new Node(val);
    }
}
