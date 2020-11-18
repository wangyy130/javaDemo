package com.wyy.javademo.suanfa.class10;


import java.util.ArrayList;
import java.util.List;

/**
 * 图的节点
 */
public class Node {

    //入度
    public int in;
    //出度
    public int out;
    //值
    public int value;
    //邻接点，直接邻居
    public List<Node> nexts;
    //临边
    public List<Edge> edges;


    Node(int value){
        in = 0;
        out = 0;
        this.value = value;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
