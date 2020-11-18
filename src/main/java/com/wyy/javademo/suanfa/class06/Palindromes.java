package com.wyy.javademo.suanfa.class06;

import javax.swing.*;
import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 */
public class Palindromes {

    public static class Node{
        public Node next;
        public int value;

        Node(int value){
            this.value = value;
        }
    }

    /**
     * 遍历链表将所有数放进stack中，开始逐个比对
     */
    public boolean palindromesByStack1(Node head){
        Stack<Integer> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur.value);
            cur = cur.next;
        }
        boolean flag = true;
        Node cur2 = head;
        while (cur2 != null){
            if(cur2.value !=  stack.pop()){
                return false;
            }
            cur2 = cur2.next;
        }
        return  flag;
    }

    //利用快慢指针，找出中点，将中点之后的数据放入栈空间中
    //遍历栈空间中的值，是否与链表前半部分相同，若相同则为回文结构
    public boolean palindromesStack2(Node head){
        if(head == null  || head.next == null){
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        Node slow = head;
        Node fast = head;
        while (fast.next !=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node cur = slow.next;

        //将slow后面的节点依次放入栈空间中
        while (cur != null){
            stack.push(cur.value) ;
            cur = cur.next;
        }

        while (!stack.isEmpty()){
            if(stack.pop() != head.value){
                return false;
            }
            head = head.next;
        }
        return  true;

    }



    //通过快慢指针找到中点，将中点之后的指针序列反转，然后开始逐个遍历，若完全相等则为回文序列
    public boolean palindromes3(Node head){
        if(head == null  || head.next == null){
            return true;
        }

        Node n1 = head;
        Node n2 = head;
        while (n2.next !=null && n2.next.next !=null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // n1 为中点

        n2 = n1.next; //中点之后第一个节点
        n1.next = null;
        Node n3 = null;

        //后半部分进行反转
        while (n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1  = n2;
            n2 = n3;
        }

        //记录末尾的节点
        n3 = n1; //P3为末尾几点
        n2 = head; //记录头结点

        boolean flag = true;

        while (n1 != null && n2 != null){
            if(n2.value != n1.value){
                flag = false;
                break;
            }

            n2 = n2.next;
            n1 =  n1.next;
        }

        //n2现在是中点
        //更正回原来的链表结构
        n1 = n3.next;
        n3.next = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return flag;
    }


}
