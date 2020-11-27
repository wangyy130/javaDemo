package com.wyy.javademo.Leetcode;

/**
 * 合并两个有序链表
 */
public class leetcode_021 {



    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null && l2 == null){
                return null;
            }


            if(l1 == null){
                return l2;
            }

            if(l2 == null){
                return l1;
            }

            ListNode h = l1.val <= l2.val ? l1 : l2;

            ListNode h1 = null;
            ListNode h2 = null;

            h1 = h == l1 ? l1.next : l1 ;
            h2 = h == l2 ? l2.next : l2;

            ListNode cur = h;
            while(h1 != null && h2  != null){

                ListNode next =  h1.val <= h2.val ? h1 : h2;

                h1 = next == h1 ? h1.next : h1;
                h2 = next == h2 ? h2.next : h2;

                cur.next = next;
                cur = next;

            }


            while(h1 != null){
                cur.next = h1;
                cur = h1;
                h1 = h1.next;
            }

            while(h2 != null){
                System.out.println("cur.val = " + cur.val);
                cur.next = h2;
                cur = h2;
                h2 = h2.next;

            }

            return h;
        }
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(-9);
        l1.next = new ListNode(3);


        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(7);

        System.out.println(Solution.mergeTwoLists(l1,l2));
    }
}
