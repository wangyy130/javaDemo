package com.wyy.javademo.suanfa.class10;

/**
 * 图中的边
 */
public class Edge {

    public int weight;

    public Node from;

    public Node to;

    Edge(int weight,Node from,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
