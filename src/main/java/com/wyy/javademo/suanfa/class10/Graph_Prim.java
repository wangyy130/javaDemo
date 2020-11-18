package com.wyy.javademo.suanfa.class10;


import javax.xml.stream.events.EntityDeclaration;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树 P算法
 *
 * 不需要并查集结构，只要一个set即可
 * 是根据点来遍历的
 *
 * 1、随机找一个节点，遍历他所有的边，找出权重最小的一个边
 * 2、判断这条边关联的两个节点是否已经被加入到set中了，如果是，则不要，如果没有加入过则要这条边
 * 直到完成所有的节点遍历
 */
public class Graph_Prim {

    public static class MyComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


    public static Set<Edge> prim(Graph graph){

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new MyComparator());
        //哪些点被解锁了
        Set<Node> nodeSet = new HashSet<>();
        //为了给边去重用的
        Set<Edge> edgeSet = new HashSet<>();


        Set<Edge> result = new HashSet<>();
        //这个最外层的循环是为了防止森林的出现,其实遍历只需要其中一个点就可以遍历完成。
        for(Node node : graph.nodes.values()){

            if(!nodeSet.contains(node)){
                nodeSet.add(node);

                //将node所有的出度边都放入小根堆中
                for (Edge e: node.edges) {
                    if(!edgeSet.contains(e)){
                        priorityQueue.add(e);
                        edgeSet.add(e);
                    }
                }

            }

            while (!priorityQueue.isEmpty()){
                Edge edge = priorityQueue.poll();

                Node to = edge.to;

                if(!nodeSet.contains(to)){
                    nodeSet.add(to);
                    result.add(edge);

                    for (Edge e: to.edges) {
                        if(!edgeSet.contains(e)){
                            edgeSet.add(e);
                            priorityQueue.add(e);
                        }
                    }
                }
            }

//            break;

        }

        return result;
    }
}
