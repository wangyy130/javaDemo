package com.wyy.javademo.suanfa.class10;


/**
 * 根据一个矩阵生成一个图结构
 */
public class GenerateGraph {

    /**
     * 根据一个N * 3 的矩阵生成一个图结构
     * 权重 ，from ， to
     * @param matrix 矩阵
     * @return 图结构
     */
    public Graph generateGraph(Integer[][] matrix){
        if(matrix == null || matrix.length == 0){
            return null;
        }

        Graph graph = new Graph();

        //matrix是一个二维数组，每一行是一个一维数组，都有三个元素，weight，from，to
        for (Integer[] integers : matrix) {
            Integer weight = integers[0]; //边的权重
            Integer from = integers[1]; //from点
            Integer to = integers[2]; //to点


            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }

            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            //出度+1
            fromNode.out++;
            //to的入度+1
            toNode.in++;

            //from的邻接点添加to
            fromNode.nexts.add(toNode);

            Edge edge = new Edge(weight, fromNode, toNode);

            fromNode.edges.add(edge);

            graph.edges.add(edge);

        }

        return graph;
    }
}
