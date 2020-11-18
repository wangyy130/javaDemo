package com.wyy.javademo.suanfa.class06;

/**
 * 链表分区问题
 * 将单链表按某值分成左边小，中间相等，右边大的三部分
 */
public class LinkPartition {

    public static class Node{
        public Node next;
        public int value;

        Node(int value){
            this.value = value;
        }
    }

    //利用荷兰国旗方法，将所有链表放入一个数组中，然后进行分区
    public Node solution1(Node head , int num){

        int i = 0;
        Node cur = head;
        while (cur != null){
            i++;
            cur = cur.next;
        }

        Node [] array = new Node[i];
        cur = head;
        i = 0;
        while (cur != null){
            array[i] = cur;
            cur = cur.next;
        }

        partition(array,num);

        for( i = 1 ; i < array.length -1 ; i++){
            array[i -1].next = array[i];
        }
        array[i].next = null;
        return array[0];
    }


    public void partition(Node[] array, int num){
        int L = -1;
        int R = array.length ;
        int index = 0;
        while (index != R){

            if(array[index].value > num){
                swap(array, index, --R);
            }else if(array[index].value < num){
                swap(array , index++ , ++L);
            }else if(array[index].value == num){
                index++;
            }
        }

    }


    public static void swap(Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //将链表分为三部分，每个部分都定义一个头指针和尾指针
    public Node solution2(Node head, int num){

        Node sh = null;
        Node st = null;

        Node eh = null;
        Node et = null;

        Node bh = null;
        Node bt = null;


        Node next = null;
        while (head != null){
            next = head.next;
            head.next = null;
            if(head.value < num){
                if(sh == null){
                    sh = head;
                }else{
                    sh.next = head;
                }
                st = head;
            }else if(head.value == num){
                if(eh == null){
                    eh = head;
                }else{
                    eh.next = head;
                }

                et = head;
            }else{
                if(bh == null){
                    bh = head;
                }else{
                    bh.next = head;
                }
                bt = head;
            }
            head = next;
        }

        //现在分成了三部分

        if(st != null){
            st.next = eh;
            et = et == null ? st : et;
        }

        if(et != null){
            et.next = bh;
        }

        return sh != null ? sh : (eh != null ? eh : bh);


    }




}
