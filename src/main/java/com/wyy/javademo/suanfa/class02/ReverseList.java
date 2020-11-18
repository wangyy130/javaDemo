package com.wyy.javademo.suanfa.class02;

/**
 * 反转链表
 */
public class ReverseList {

    /*
    单链表
     */
    public static class Node {
        public Node next;
        public int value;

        Node(int value){
            this.value=value;
        }

    }

    /*
    双链表
     */
    public static class DoubleNode{
        public DoubleNode pre;
        public DoubleNode next;
        public int value;

        DoubleNode(int value){
            this.value=value;
        }

    }

    /**
     * 反转单链表
     *
     */
    public static Node reverseNode(Node head){

        Node pre = null;
        Node next = null;

        while(head != null){
            next = head.next; //先记录next的节点

            head.next = pre; //修改当前节点反转之后的next节点，因为是单列表，所以只需要更正next属性

            pre = head; //为下一个节点的pre进行归位

            head = next; //继续循环下一个节点，必须能让head不断循环下去

        }

        return pre;
    }


    /*
    反转双端链表
     */
    public static DoubleNode reverseDoubleNode(DoubleNode head){
        DoubleNode pre = null; //当前节点的pre变量
        DoubleNode next = null;

        while(head != null){

            next =  head.next; //记录head节点的next变量

            head.pre = next;
            head.next = pre;

            pre = head ;
            head = next;

        }

        return pre;
    }







}
