package com.wyy.javademo.suanfa.class06;

/**
 * 两个链表，可能有环，也可能无环，判断两个链表第一个相交节点，如果不想交，则返回null
 *
 * 注意： 单链表，每个节点只有一个next指针，如果一个链表呀有环，一个没环，一定不会相交
 *
 * 分几种情况
 * 1、两个链表都无环 ， 1、相交， 2、没有相交
 * 2、有环
 *      a、两个链表都有环 但不想交
 *      b、如果有环相交，那么两个链表一定共用一个环
 *          1、共用的环是同一个入环点
 *          2、共用的环不是同一个入环点
 */
public class TheFirstIntersectNode {

    public static class Node{
        public Node next;
        public int value;

        Node(int value){
            this.value = value;
        }
    }


    //1.判断一个链表是否有环，如果有环则返回第一个入环的节点, 可以用set方法替代
    public Node firstNodeOfLoop(Node head){

        //小于3个节点肯定无环
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }

        Node n1 = head.next;
        Node n2 = head.next.next;


        while (n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = head;
        while (n1 != n2){
            n2 = n2.next;
            n1 = n1.next;
        }

        return n2;

    }

    //如果两个链表无环，求第一个相交点
    public Node noLoop(Node head1, Node head2){

        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;

        //先遍历两个链表求出二者节点的差值
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }

        //如果最后一个节点二者不相等，则说明没有相交节点
        if(cur1 != cur2){
            return null;
        }

        cur1 = n > 0 ? head1 : head2; // cur1为长链表
        cur2 = cur1 == head1 ? head2 : head1; //cur2 为短链表

        n = Math.abs(n);
        while (n != 0 && cur1 != null){
            n--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2 && cur1 != null  && cur2 != null){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }


    //如果两个有环的链表，求第一个入环点
    public Node bothLoop(Node head1, Node loop1, Node head2 , Node loop2){
        //如果两个入环的点相同，那么求第一个相交节点就是类似于求两个链表的第一个相交节点
        if(loop1 == loop2){
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0 ;

            while (cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }
            if(cur1 != cur2){
                return null;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            n = Math.abs(n);

            while (n != 0 ){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            Node cur1 = loop1.next;
            while (cur1 != loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }


        }
        return null;
    }



    //主方法
    public Node getFirstIntersectNode(Node head1 , Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        Node loop1 = firstNodeOfLoop(head1);
        Node loop2 = firstNodeOfLoop(head2);

        //两个链表都无环
        if(loop1 == null && loop2 == null){
            return noLoop(head1 , head2);
        }


        if(loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }

        return null;
    }


}
