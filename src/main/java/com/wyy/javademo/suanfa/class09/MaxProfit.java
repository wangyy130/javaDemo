package com.wyy.javademo.suanfa.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

//项目最大收益问题
public class MaxProfit {

    class Program{
        public int c; //花费
        public int p; //收益
        Program(int c , int p){
            this.c = c;
            this.p = p;
        }
    }

    //w : 初始资金
    // K: 最多可以做几个项目
    // profits : 每个项目对应的收益
    // costs : 每个项目对应的花费
    public int maxProfit(int W, int K, int[] profits,int[] costs){
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());

        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        //将所有的项目按照最小花费放入小根堆
        for(int i = 0; i < profits.length ; i++){
            minCostQ.add(new Program(costs[i],profits[i]));
        }

        //循环K次，说明一共可以做K个项目
        for(int j = 0 ; j < K; j++){

            while (!minCostQ.isEmpty()){
                if(minCostQ.peek().c <= W){
                    //将所有花费小于W的放入收益大根堆
                    maxProfitQ.add(minCostQ.poll());
                }
            }
            //如果收益大根堆为空，则直接返回，不能做项目
            if(maxProfitQ.isEmpty()){
                return W;
            }

            //每次从收益大根堆中弹出一个利润最大的项目 的收益 就是最大的收益
            W += maxProfitQ.poll().p;


        }

        return W;
    }

    //花费小根堆比较器
    class MinCostComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    //利润大根堆
    class MaxProfitComparator implements  Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
