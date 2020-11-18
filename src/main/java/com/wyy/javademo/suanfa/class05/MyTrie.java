package com.wyy.javademo.suanfa.class05;


import java.util.HashMap;

/**
 * 前缀树基本结构
 */
public class MyTrie {

    class Node{
        int pass;
        int end;
        HashMap<Integer,Node> nexts;

        Node(){
            pass = 0;
            end = 0 ;
            nexts = new HashMap<>();
        }
    }

    Node root = new Node();

    private void insert(String word){
        if(word == null){
            return;
        }

        Node node = root;
        node.pass++;

        char[] chs = word.toCharArray();
        int index = 0;
        for (int i = 0 ; i< chs.length - 1; i++){
            index = (int)chs[i] ;
            if(!node.nexts.containsKey(index)){
                node.nexts.put(index,new Node());
            }

            node = node.nexts.get(index);
            node.pass++;

        }

        node.end++;
    }


    private int search(String word){
        if(word == null){
            return 0;
        }

        Node node = root;

        char[] chs = word.toCharArray();

        int index = 0;
        for (int i = 0 ; i < chs.length - 1 ; i++){
            index = (int)chs[i];
            if(!node.nexts.containsKey(index)){
                return 0;
            }
            node = node.nexts.get(index);
        }

        return node.end;

    }



    private void delete(String word){
        if(word == null){
            return;
        }
        if(search(word) != 0){
            Node node = root;
            node.pass--;

            char[] chs = word.toCharArray();
            int path = 0;

            for (int i = 0 ; i< chs.length - 1; i++){
                path = chs[i];

                if(--node.nexts.get(path).pass == 0){
                    node.nexts.remove(path) ;
                }
                node = node.nexts.get(path);
            }

            node.end--;

        }
    }


    private int prefixNumber(String word){
        if(word == null){
            return 0;
        }


        Node node = root;

        char[] chs = word.toCharArray();
        int path = 0;

        for (int i  = 0 ; i < chs.length - 1 ; i++){
            path = chs[i];

            if(!node.nexts.containsKey(path)){
                return 0 ;
            }
            node = node.nexts.get(path);
        }

        return node.pass;

    }

}
