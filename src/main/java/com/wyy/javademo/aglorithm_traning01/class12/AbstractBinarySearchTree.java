package com.wyy.javademo.aglorithm_traning01.class12;

/*
    二叉搜索树的增删改查
    1、增，比当前节点小的都在左边，比他大的都放右边
    2、查询
    3、删
        删除首先进行查询，只有存在该节点才能进行删除
        1、如果当前节点无孩子，则直接删除
        2、如果有左孩子或右孩子，则将其左孩子或右孩子替换当前节点即可
        3、既有左孩子又有右孩子：
            用后继节点代替当前节点(后继节点为右子树上最左节点)
            如果后继节点正好为当前节点的右孩子，则不用再进行顶替，直接删除即可
 */
public class AbstractBinarySearchTree {

    //根节点
    private Node root;

    private int size;

    //生成一个节点
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }


    /*
        二叉搜索树查询
     */
    public Node search(int value){
        Node node = root;
        while(node != null && node.value !=null && node.value != value){
            if(value < node.value){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return node;
    }


    /*
        二叉搜索树插入并返回插入节点
     */
    public Node insert(int element){
        if(root == null || root.value == null){
            root = createNode(element,null,null,null);
            size++;
            return root;
        }

        Node curNode = root;
        //先定位要插入的节点的父节点
        Node insertParentNode = null;
        while(curNode != null && curNode.value != null){
            insertParentNode = curNode;
            if(element < curNode.value){
                curNode = curNode.left;
            }else if(element > curNode.value){
                curNode = curNode.right;
            }
        }

        //创建要插入的新节点
        Node newNode = createNode(element,insertParentNode,null,null);

        if(newNode.value > insertParentNode.value){
            insertParentNode.right = newNode;
        }else{
            insertParentNode.left = newNode;
        }
        return newNode;
    }

    /*
        二叉搜索树删除某个节点并返回
     */
    public Node delete(int element){
        Node node = search(element);
        if(node != null){
            return delete(node);
        }else{
            return null;
        }
    }

    //真正的删除方法
    protected Node delete(Node deleteNode){
        return null;
    }





    /*
        节点对象
     */
    static class Node{
        private Integer value;
        private Node parent;
        private Node left;
        private Node right;

        Node(Integer value, Node parent,Node left, Node right){
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }
    }
}
