package com.wyy.javademo.suanfa.class06;

import java.util.ArrayList;
import java.util.List;

/**
 * 快慢指针问题
 */
public class SlowFastPos {

    //单链表结构
    public static class Node{
        public Node next;
        public int value;

        Node(int value){
            this.value = value;
        }
    }

    //奇数长度返回中点，偶数长度返回中点的上方
    public Node MidAndMidUp(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next !=null){
            slow = head.next;
            fast = head.next.next;
        }

        return slow;
    }


    public Node MidAndMidUpByList(Node head){
        if(head == null){
            return head;
        }

        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null){
            list.add(head);
            cur = head.next;
        }
        int length = list.size();
        return list.get((length - 1) / 2);
    }


    //奇数个返回中点，偶数个返回中点的下方
    public Node MidAndMidDown(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public Node midAndMidDownByList(Node head){
        if(head == null){
            return null;
        }

        List<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null){
            list.add(cur);
            cur = cur.next;
        }
        return list.get(list.size() / 2);
    }


    /**
     * 奇数个数返回中点前一个，偶数返回上中点前一个
     * @param head
     * @return
     */
    public Node midUpOrMidPre(Node head){

        if(head == null  || head.next == null){
            return  null;
        }

        if(head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;

        while (fast.next !=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //奇数返回中点上一个，偶数返回下中点的上一个
    public Node midUpOrMidNext(Node head){

        if(head == null || head.next == null ){
            return null;
        }

        if(head.next.next == null){
            return head;
        }

        Node slow = head;
        Node fast = head.next;

        while (fast.next !=null  && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;

    }





}
