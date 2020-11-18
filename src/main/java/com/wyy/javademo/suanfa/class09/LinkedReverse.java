package com.wyy.javademo.suanfa.class09;

import java.util.*;

public class LinkedReverse {

    public static class Node {
        public Node next;

        public int value;

        Node(int value){
            this.value = value;
        }
    }


    public static  Node reverse(Node head){
        if(head == null){
            return null;
        }


        Node pre = null;
        Node next = null;

        while (head != null){
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;

        }


        return pre;
    }

    public static Node reverseFromMToN(Node head,int m ,int n){

        if(head == null){
            return null;
        }

        int i = 1;
        Node cur = head;

        Queue<Integer> s1 = new LinkedList<>();
        Stack<Integer> s2 = new Stack<>();
        Queue<Integer> s3 = new LinkedList<>();
        while (cur != null){
            if(i < m){
                s1.add(cur.value);
            }
            if(i >= m  && i <= n){
                s2.push(cur.value);
            }

            if(i > n){
                s3.add(cur.value);
            }

            cur = cur.next;
            i++;
        }

        cur = new Node(!s1.isEmpty() ? s1.poll() : (!s2.isEmpty()? s2.pop() : s3.poll()));
        head = cur;
        while (!s1.isEmpty()){
            cur.next = new Node(s1.poll());
            cur = cur.next;
        }

        while (!s2.isEmpty()){
            cur.next = new Node(s2.pop());
            cur = cur.next;
        }


        while (!s3.isEmpty()){
            cur.next = new Node(s3.poll());
            cur = cur.next;
        }

        return head;
    }





    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node.next = null;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;


        Node cur = reverseFromMToN(node, 1, 1);
        while (cur != null){
            System.out.printf("" + cur.value);
            cur = cur.next;
        }



    }

}
