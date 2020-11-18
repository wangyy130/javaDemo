package com.wyy.javademo.suanfa.class02;

/**
 * 在一个单链表上，删除给定的值得节点
 * 1、首先判断head节点上是否有给定的值
 * 2、再从head节点开始遍历整个链表
 */
public class DeleteGivenValueList {

    public static class Node{
        public Node next;
        public int value;

        Node(int value){
            this.value=value;
        }
    }

    public static Node deleteGivenValueList(Node head,int num){

        //先判断head节点是否为num，寻找出第一个不是num的值作为head节点
        while(head != null){
            if(head.value == num){
                head = head.next;
            }else{
                break;
            }
        }

        Node pre = head; //这里是前节点为head节点 ？？？
        Node cur = head;

        while(cur != null){

            if(cur.value == num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


}
