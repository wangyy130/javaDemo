package com.wyy.javademo.suanfa.class10;


import java.util.*;

/**
 * K算法 图的最小生成树
 *
 * 要用到并查集结构
 *
 * 所有的点连在一起，但是只用整体权重最小的边的集合
 *
 *
 * 找权重最小的边所连接的两个点，如果不在一个集合就要这个边，否则 不要
 * 主要利用并查集解决连通性问题
 */
public class Graph_Kruskal {


    public static class UnionFind{

        public Map<Node,Node> parentMap = new HashMap<>();
        public Map<Node,Integer> sizeMap = new HashMap<>();

        UnionFind(Collection<Node> list){
            for(Node cur : list){
                parentMap.put(cur,cur);
                sizeMap.put(cur,1);
            }
        }


        /**
         * 找node的父节点
         */
        public Node findFather(Node node){

            Queue<Node> path = new LinkedList<>();
            while (parentMap.get(node) != node){
                path.add(node);
                node = parentMap.get(node);
            }

            while (!path.isEmpty()){
                parentMap.put(path.poll(),node);
            }

            return node;
        }


        //判断两个节点的父节点是否一样，是否在同一个集合内
        public boolean isSameSet(Node a, Node b){
            return findFather(a) == findFather(b);
        }


        //合并a节点和b节点
        public void unionSet(Node a, Node b){
            if(parentMap.get(a) == null || parentMap.get(b) == null){
                return ;
            }

            Node aF = findFather(a);
            Node bF = findFather(b);

            if(aF != bF){
                int aSize = sizeMap.get(a);
                int bSize = sizeMap.get(b);

                Node big = aSize >= bSize ? a : b;
                Node small =  big == a ? b : a;

                parentMap.put(small,big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

    }


    //定义比较器
    public static class MyComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


    /*
        1、将所有的节点放入并查集中
        2、将所有的边放入小根堆中排序
        3、每次从小根堆中取边权重最小的边，判断两个节点是否在一个集合中
            如果不是一个集合，我们就要这个边，并且对两个节点求并集，直到所有的边都遍历完成
     */
    public static Set<Edge> kruscal(Graph graph){

        //将所有的节点放入并查集中
        UnionFind unionFind = new UnionFind(graph.nodes.values());

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new MyComparator());
        //将所有的边放入小根堆中
        priorityQueue.addAll(graph.edges);

        Set<Edge> edgeSet = new HashSet<>();

        while (!priorityQueue.isEmpty()){

            Edge edge = priorityQueue.poll();
            Node fromNode = edge.from;
            Node toNode = edge.to;

            if(!unionFind.isSameSet(fromNode,toNode)){
                edgeSet.add(edge);
                unionFind.unionSet(fromNode,toNode);
            }
        }

        return edgeSet;

    }














    //k算法 求最小生成树
    public Set<Edge> Kruskal2(Graph graph){

        if(graph == null || graph.edges.isEmpty()){
            return null;
        }
        UnionFind unionFind = new UnionFind(graph.nodes.values());

        PriorityQueue<Edge> edgeQ = new PriorityQueue<>(new MyComparator());
        //将所有边加入小根堆
        edgeQ.addAll(graph.edges);

        Set<Edge> edgeSet = new HashSet<>();


        while (!edgeQ.isEmpty()){

            Edge edge = edgeQ.poll();
            Node from = edge.from;
            Node to = edge.to;

            if(!unionFind.isSameSet(from,to)){
                edgeSet.add(edge);
                unionFind.unionSet(from,to);
            }
        }

        return edgeSet;

    }



}
