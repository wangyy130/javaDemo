package com.wyy.javademo.suanfa.class10;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 封装图结构
 */
public class Graph {

    //所有点的集合
    public Map<Integer,Node> nodes;
    //所有边的集合
    public Set<Edge> edges;

    Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
