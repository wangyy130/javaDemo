package com.wyy.javademo.suanfa.class10;


import java.util.*;

/**
 * 图的深度优先遍历
 *
 * 主要是利用栈和set实现
 *
 */
public class Graph_DFS {

    /*8
        加入就打印
     */
    public static void DFS(Node node){
        if(node == null){
            return ;
        }

        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.print(node.value);

        while (!stack.isEmpty()){
            Node cur = stack.pop();

            for (Node n : cur.nexts){
                //如果后代中有没进入过set中时，先将cur压入，再压入n，为了记录cur
                if(!set.contains(n)){
                    stack.push(cur);
                    stack.push(n);
                    set.add(n);
                    //压入就打印，压入后代就打印
                    System.out.print(n.value);
                    break;
                }
            }
        }
    }
}
