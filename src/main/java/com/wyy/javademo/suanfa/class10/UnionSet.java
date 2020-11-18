package com.wyy.javademo.suanfa.class10;


import java.util.*;

/**
 * 并查集：用来解决连续性问题
 */
public class UnionSet<V> {

    public static class Node<V> {
        V value;
        Node(V value){
            this.value = value;
        }
    }

    //任何一个值V都对应一个节点，nodes为节点表，一一对应，初始化之后，永远不会再改动，只做记录
    public Map<V,Node<V>> nodes = new HashMap<>();
    //记录每个节点的父亲节点
    public Map<Node<V>,Node<V>> parentMap = new HashMap<>();
    //只有代表点才会在parentSize中有记录，就是每个独立的集合最大的那个父亲节点才会有记录
    public Map<Node<V>,Integer> parentSize = new HashMap<>();

    UnionSet(List<V> list){
        for (V cur : list){
            Node<V> node = new Node<>(cur);
            nodes.put(cur, node);
            parentMap.put(node, node);
            parentSize.put(node, 1);
        }
    }


    //寻找当前节点的父节点
    public Node<V> findFather(Node<V> cur){

        Queue<Node<V>> path = new LinkedList<>();
        while(cur != parentMap.get(cur)){
            path.add(cur);
            cur = parentMap.get(cur);
        }

        while (!path.isEmpty()){
            parentMap.put(path.poll(),cur);
        }

        return cur;
    }


    //判断是否为一个集合
    public boolean isSameSet(V a, V b){
        if(!nodes.containsKey(a) || !nodes.containsKey(b)){
            return false;
        }

        return findFather(new Node<>(a)) == findFather(new Node<>(b));
    }


    public void union(V a, V b){

        if(!nodes.containsKey(a) || !nodes.containsKey(b)){
            return;
        }

        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));

        if(aHead != bHead){
            int aSize = parentSize.get(aHead);
            int bSize = parentSize.get(bHead);

            Node<V> big = aSize >= bSize ? aHead : bHead;
            Node<V> small = big == aHead ? bHead : aHead;

            parentMap.put(small,big);
            parentSize.put(big, aSize + bSize);
            parentSize.remove(small);
        }
    }

    //返回集合中有多少个单独的集合
    public int getNum(){
        return parentSize.size();
    }
}
