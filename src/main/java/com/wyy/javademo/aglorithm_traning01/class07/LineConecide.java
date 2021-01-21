package com.wyy.javademo.aglorithm_traning01.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    线段重合问题
    利用堆来解决

    先将所有线段按照start进行排序
    开始遍历所有的line
    每次遍历line时，先判断 endHeap中是否有end比当前start还小的line，如果有，移除
    然后将当前line加入endHeap中，此时endHeap中的线段数就是戒指目前line能够覆盖到的线段数量

    最后求max

 */
public class LineConecide {

    static class Line{
        public int start;
        public int end;

        Line(int start, int end){
            this.start = start;
            this.end = end;
        }
    }


    /**
     * 最多有多少个线段重叠有交叉
     *
     */
    public  static  int maxCover(int[][] m){
        Line[] lines = new Line[m.length];

        for (int[] l:m) {
            Line line = new Line(l[0],l[1]);
        }
        int max = 0;
        //先按start排序
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Line> priorityQueue = new PriorityQueue<>(new EndComparator());
        for (Line line: lines) {
            while (!priorityQueue.isEmpty() && line.start >= priorityQueue.peek().end){
                priorityQueue.poll();
            }

            priorityQueue.add(line);
            max = Math.max(max,priorityQueue.size());
        }

        return max;
    }

    static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }


    static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }
}
