package com.wyy.javademo.suanfa.class02;

/**
 * 双端队列实现队列和栈
 */
public class DoubleEndsQueue {

    public static class Node<T> {
        public Node<T> pre;
        public Node<T> next;
        public T value;

        Node(T value){
            this.value=value;
        }
    }

    public static class DoubleLinkList<T>{
        public Node<T> head;
        public Node<T> tail;

        //从头部添加节点
        public void addFromHead(T data){
            Node<T> cur=new Node<T>(data);
            if(head == null){
                head = cur;
                tail = cur;
            }else{
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
        }

        //从尾部添加节点进队列
        public void addFromTail(T data){
            Node<T> cur=new Node<T>(data);

            if(head == null){
                head = cur;
                tail = cur;
            }else {
                tail.next = cur;
                cur.pre = tail;
                tail = cur;

            }

        }

        //从头部取出节点
        public T peekFromHead(){

            if (head == null ){
                return null;
            }

            Node<T> cur =  head ;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;
                cur.next = null;
                head.pre = null;
            }

            return cur.value;
        }

        // 从尾部取数据
        public T peekFromTail(){

            if(head == null){
                return null;
            }

            Node<T> cur = tail;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                tail = tail.pre;
                tail.next = null;
                cur.pre = null;
            }

            return cur.value;
        }
    }


    //用双端队列实现栈
    public static class MyStack<T>{
        public DoubleLinkList<T> mystack;

        MyStack(){
            mystack = new DoubleLinkList<>();
        }
        public void pushData(T data){
            mystack.addFromHead(data);
        }

        public T popData(){
            return mystack.peekFromTail();
        }

    }


    //用双端列表实现队列
    public static class MyQueue<T>{
        public DoubleLinkList<T> myqueue;

        MyQueue(){
            myqueue = new DoubleLinkList<>();
        }

        public void addData(T data){
            myqueue.addFromTail(data);
        }

        public T getData(){
            return myqueue.peekFromHead();
        }
    }


}
