package com.wyy.javademo.suanfa.class07;


/**
 * 前驱和后继节点
 *
 * 都是相对于中序遍历 左，头，右
 */
public class SuccessorNode {

    public static class Node{
        public Node left;
        public Node right;
        public Node parent; //父亲节点

        public int value;

        public Node(int v){
            value = v;
        }
    }



    /*
        获取给定节点的后继节点
        1、如果该节点有右孩子，那么他的右子树的最左孩子为他的后继节点
        2、如果该节点无右孩子，那么要看他所在的树是否为其父亲节点的左子树，如果是左子树，则父亲节点为他的后继节点
     */
    public Node getSuccessorNode(Node node){
        if(node == null){
            return null;
        }

        Node successor = null;
        //如果右孩子不为空，找他的右树上的最左子树
        if(node.right != null){
            successor = node.right;
            while (successor.left != null){
                successor = successor.left;
            }
        }else{
            //如果右孩子为空
            Node parent = node.parent;

            while (parent != null && parent.left != node){
                node = parent;
                parent = parent.parent;
            }

            //假如，node是最后一个节点，那么while循环到parent = null时会结束 所以后继节点也会变成null
            successor = parent;
        }

        return successor;

    }


    /**
     * 获取前驱节点 左 头 右
     *
     * 1、看是否有左子树，如果有，则左子树上的最右节点为其前驱节点
     * 2、如果没有左孩子，如果x节点有父节点且为父亲节点的右孩子，或者遍历向上直到成为某个节点的右孩子，则这个节点为x的前驱节点
     *
     */
    public Node getPreNode(Node node){
        if(node == null){
            return null;
        }

        //如果左子树不为空
        if(node.left != null){
            //找到左子树上面的最右节点
            Node successor = node.left;
            while (successor.right != null){
                successor = successor.right;
            }
            return successor;
        }
        //否则找到当前子树是父亲节点的右子树
        Node parent = node.parent;

        while (parent != null && parent.right != node){
            node = parent;
            parent = parent.parent;

        }

        return parent;
    }


}
