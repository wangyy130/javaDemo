package com.wyy.javademo.suanfa.class10;

import java.util.*;

/**
 * 图的拓扑排序
 *
 * 将所有入度为0的点加入到一个集合中，每次弹出一个入度为0的，直到所有的点都弹出
 */
public class Graph_TopoSort {


    public static List<Node> topoSort(Graph graph){

        //记录所有节点的入度,这样可以不破坏原有的图结构
        //key ： node为一个节点，value ： Integer为还剩多少入度
        Map<Node,Integer> inMap = new HashMap<>();

        //记录所有入度为0的节点，进入这个队列
        Queue<Node>  zeroQueue = new LinkedList<>();


        for(Node n : graph.nodes.values()){
            inMap.put(n,n.in);

            if(n.in == 0){
                zeroQueue.add(n);
            }
        }


        List<Node> result = new ArrayList<>();

        while (!zeroQueue.isEmpty()){
            Node cur = zeroQueue.poll();
            result.add(cur);
            //对于加入入度为0的队列中的点，每个点的next（出度的点）的入度-1
            for (Node n : cur.nexts){
                inMap.put(n , inMap.get(n) - 1);
                //如果入度-1之后变成了0，则继续加入入度为0的队列
                if(inMap.get(n) == 0){
                    zeroQueue.add(n);
                }
            }
        }

        return result;

    }






















    //对一个图进行拓扑排序
    public List<Node> topoSort2(Graph graph){

        if(graph == null || graph.nodes.isEmpty()){
            return null;
        }

        Map<Node,Integer> inMap = new HashMap<>();

        Queue<Node> zeroQ = new LinkedList<>();
        for (Node node: graph.nodes.values()) {
            inMap.put(node,node.in);

            if(node.in == 0){
                zeroQ.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zeroQ.isEmpty()){
            Node cur = zeroQ.poll();
            result.add(cur);
            for (Node n: cur.nexts) {
                int in = inMap.get(cur) - 1;
                inMap.put(n,in);
                if(in == 0){
                    zeroQ.add(n);
                }

            }
        }

        return  result;
    }
}
