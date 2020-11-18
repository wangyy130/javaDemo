package com.wyy.javademo.suanfa.class10;


import java.util.*;

/**
 * 图的宽度优先遍历 breadth-first-search
 *
 * 利用队列和set进行（因为图中可能有环，二叉树不会有环只用队列就可以）
 */
public class Graph_BFS {

    /*
        从node出发进行图的宽度优先遍历,一层一层遍历

        利用队列和set

        每次出队即打印

     */
    public static void BFS(Node node){
        if(node == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();

        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            System.out.print(cur.value);

            for (Node n : node.nexts){
                if(!set.contains(n)){
                    queue.add(n);
                    set.add(n);
                }
            }

        }

    }
}
