package com.wyy.javademo.suanfa.class07;

/**
 * 二叉树打印问题
 */
public class PrintBinaryTree {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }


    public static void printNode(Node head){
        System.out.println("print in order");
        printInOrder(head, 0 , "H", 17);
    }


    /**
     * 按顺序打印二叉树
     * @param head 二叉树头结点
     * @param height 打印的节点的高度，即所处在第几层
     * @param to 节点标志
     * @param len 每个节点所占用的额宽度
     */
    public static void printInOrder(Node head, int height ,String to , int len){

        if(head == null){
            return;
        }

        printInOrder(head.right, height + 1, "v" , len);
        String val = to +  head.value + to; //值占用的
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);

        System.out.println(getSpace(height * len) + val);

        printInOrder(head.right, height + 1, "^", len);
    }


    //空格填充
    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }


    public static void main(String[] args) {
        Node node = new Node(5222);
        Node node1 = new Node(811);
        Node node2 = new Node(53333);
        Node node3 = new Node(5);
        Node node4 = new Node(434335);
        Node node5 = new Node(1115);
        Node node6 = new Node(-52222);
        Node node7 = new Node(222233);

        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;

        printNode(node);



    }
}
