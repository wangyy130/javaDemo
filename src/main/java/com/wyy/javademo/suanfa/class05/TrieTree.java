package com.wyy.javademo.suanfa.class05;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 */
public class TrieTree {


    public static class Node1{
        public int pass; //通过次数
        public int end ; //结束次数
        public Node1[] nexts;

        public Node1(){
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class MyTrieTree1{
        private Node1 root = new Node1(); //初始化一个头节点

        //向前缀树中插入一个元素
        public void push(String param){
            if(param == null || "".equals(param)){
                return;
            }

            Node1 node = root;
            node.pass++;
            int path = 0;
            char[] chs = param.toCharArray();

            for (int i = 0; i < chs.length ; i++){

                path = chs[i] - 'a';
                if(node.nexts[path] == null){
                    node.nexts[path] = new Node1();
                }
                node.pass++;
                node = node.nexts[path]; //继续为下一轮循环node赋值

            }
            node.end++;

        }


        /*
            查询当前这个word之前被加入过node中几次
         */
        public int search(String word){
            if(word == null || "".equals(word)){
                return 0;
            }

            Node1 node= root;
            char[] chs = word.toCharArray();
            int path = 0;

            for(int i= 0 ; i < chs.length ; i++){
                path = chs[i] - 'a';
                if(node.nexts[path] == null || (node.nexts[path]).pass == 0){
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }


        /*
            在前缀树中删除word，只删除一次
         */
        public void delete(String word){

            if(search(word) != 0 ){

                Node1 node = root;
                node.pass--;

                char[] chs = word.toCharArray();
                int path = 0;

                for(int i = 0; i < chs.length ; i++){
                    path = chs[i] - 'a' ;

                    if(--(node.nexts[path]).pass == 0){
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }


        /*
            计算以word为前缀的字符串被加入了几次
         */
        public int prfixCount(String word){

            if(word == null || "".equals(word)){
                return 0;
            }

            Node1 node = root;
            char[] chs = word.toCharArray();
            int path = 0;

            for(int i = 0 ; i < chs.length ; i++){
                path = chs[i] - 'a';
                if(node.nexts[path] == null){
                    return 0;
                }
                node = node.nexts[path];
            }

            return node.pass;
        }

    }




    public static class Node2{
        public int pass;
        public int end;
        public Map<Integer,Node2> nexts; //用map来代替数组，扩展性更好,一个节点的下一个节点集合

        Node2(){
            pass = 0;
            end = 0 ;
            nexts = new HashMap<>();
        }
    }

    /**
     * 前缀树利用map的实现方式
     */
    public static class MyTrieTree2{

        private Node2 root =new Node2();

        public void add(String word){

            if(word == null || "".equals(word.trim())){
                return;
            }
            Node2 node = root;
            node.pass++;

            char[] chs = word.toCharArray();
            int path = 0 ;

            for (char ch : chs) {
                path = ch;

                if (!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new Node2());
                }
                Node2 next = node.nexts.get(path);
                next.pass++;
                node = next;
            }

            node.end++;

        }


        /**
         * 搜索word单词被加入了几次
         */
        public int search(String word){

            if(word == null || "".equals(word.trim())){
                return 0;
            }

            Node2 node = root;

            char[] chs = word.toCharArray();
            int path = 0;
            for (char ch : chs) {
                path = ch;
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;

        }



        public int prefixNumber(String word){
            if(word == null || "".equals(word.trim())){
                return  0;
            }

            Node2 node = root;
            char[] chs = word.toCharArray();

            int path = 0;

            for (char ch : chs) {
                path = ch;
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }


        /**
         * 删除word，只删除一次
         */
        public void delete(String word){
            if(search(word) != 0){
                Node2 node = root;
                node.pass--;

                char[] chs = word.toCharArray();
                int path = 0;
                for (char ch : chs){
                    path = ch;
                    if( --node.nexts.get(path).pass == 0 ) {
                       node.nexts.remove(path);
                       return;
                    }
                    node = node.nexts.get(path);

                }
                node.end--;

            }

        }
    }



}



