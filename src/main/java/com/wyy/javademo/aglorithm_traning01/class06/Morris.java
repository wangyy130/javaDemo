package com.wyy.javademo.aglorithm_traning01.class06;

/**
 * morris遍历
 * 时间复杂度O(N)
 * 空间复杂度O(H)
 *
 *
 *
 * 流程：
 * cur = head;
 * 1、如果没有左树，那么 cur = cur.right;
 * 2、如果有左树
 *      a、找到左树的最右孩子mostRight 如果 为null,mostRight.right = cur; cur = cur.left;
 *      b、找到左树的最右孩子mostRight 如果mostRight == cur, mostRight = null, cur = cur.right;
 *
 *
 *结论： 一个节点只要有左孩子，就一定会经过两次该节点
 * 先序：第一次经过该节点就打印
 * 中序：第二次经过该节点就打印，如果只经过一次的节点来到就打印
 *
 */
public class Morris {
    //二叉树结构
    static class Node{
        public Node left;
        public Node right;
        public int val;
        Node(int val){
            this.val = val;
        }
    }

    public static void morris(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){

            mostRight = cur.left;

            //左子树不为空
            if(mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue; //直接跳过下一个节点去判断
                }else{
                    //来到这里说明节点 mostRight.right == cur,是第二次来到当前节点了
                    mostRight.right = null;
                }
            }

            cur = cur.right;
        }

    }



    public static void morrisPre(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){

            mostRight = cur.left;

            //左子树不为空
            if(mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue; //直接跳过下一个节点去判断
                }else{
                    //来到这里说明节点 mostRight.right == cur,是第二次来到当前节点了
                    mostRight.right = null;
                }
            }
            System.out.println("第一次经过 " + cur.val);
            cur = cur.right;
        }
    }



    public static void morrisIn(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){

            mostRight = cur.left;

            //左子树不为空
            if(mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue; //直接跳过下一个节点去判断
                }else{
                    System.out.println(cur.val);
                    //来到这里说明节点 mostRight.right == cur,是第二次来到当前节点了
                    mostRight.right = null;
                }
            }else{
                //中序遍历如果只经过一次的点，第一次就打印
                System.out.println(cur.val);
            }

            cur = cur.right;
        }
    }
}
