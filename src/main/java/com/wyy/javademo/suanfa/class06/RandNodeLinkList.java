package com.wyy.javademo.suanfa.class06;

import java.util.HashMap;
import java.util.Map;

/**
 * 克隆含有random节点的单链表
 *
 * 1、通过哈希表来克隆
 *
 * 2、自己构造哈希表的关系完成
 */
public class RandNodeLinkList {

    //单链表结构
    public static class Node{
        public Node next;
        public int value;
        public Node rand;
        Node(int value){
            this.value = value;
        }
    }

    /**
     * 利用哈希表进行克隆
     */
    public Node cloneByHash(Node head){

        Map<Node,Node> map = new HashMap<>();

        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            map.get(cur).next = cur.next;
            map.get(cur).rand = cur.rand;

            cur = cur.next;
        }

        return map.get(head);
    }


    //自己构造哈希结构
    public Node clone2(Node head){

        Node cur = head;
        Node next = null;

        while (cur != null){
            next = cur.next;

            cur.next = new Node(cur.value);
            cur.next.next  = next;

            cur = next;
        }

        cur = head;
        Node curCopy = null;

        //连接rand节点
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand == null ? null : cur.rand.next; //这里要考虑到最后一个节点的情况

            cur = next;
        }

        //split
        cur = head;

        while (cur != null){

            next = cur.next.next;
            curCopy = cur.next;
            curCopy.next = next == null ? null : next.next;
            cur.next = next;

            cur = next;

        }

        return head.next;


    }



}
