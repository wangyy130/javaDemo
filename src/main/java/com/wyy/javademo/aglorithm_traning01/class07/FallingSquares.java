package com.wyy.javademo.aglorithm_traning01.class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * 矩形掉落到二维平面上时的最大高度问题
 */
public class FallingSquares {

    static  class SegmentTree{
        public int[] max;
        public boolean[] update;
        public int[] change;

        //size这里代表x轴上有多少个点，在此之前要进行离散化
        SegmentTree(int size){
            int N = size + 1;
            max = new int[N << 2];
            update = new boolean[N << 2];
            change = new int[N << 2];
        }



        public void update(int l, int r, int C, int L, int R, int rt){
            if(l <= L && R <= r){
                update[rt] = true;
                max[rt] = C;
                change[rt] = C;
            }

            int mid = (l + r) >> 1;
            //如果没有全包，则开始下发
            pushDown(rt, mid - l + 1, r - mid);
            if(l <= mid){
                update(l, mid, C, L, R, rt <<1);
            }

            if(R > mid){
                update(mid + 1, r, C, L, R, rt << 1 | 1);
            }

            //汇总
            pushUp(rt);
        }


        public int query(int l, int r, int L, int R, int rt){
            if(l <= L && R <= r){
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);

            int left = 0;
            int right = 0;
            if(l <= mid){
                left = query(l, mid ,L, R, rt <<1);
            }

            if(R > mid){
                right = query(mid + 1, r, L, R, rt << 1 | 1);
            }

            return Math.max(left,right);

        }


        public void pushDown(int rt, int ln, int rn){
            if(update[rt]){
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] =change[rt];
                update[rt] = false;
            }
        }


        public void pushUp(int rt){
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }
    }

    //对原始数组进行离散化的操作
    public HashMap<Integer,Integer> index(int[][] positions){
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int[] pos:positions) {
            treeSet.add(pos[0]);
            treeSet.add(pos[0] + pos[1] - 1);
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        //位置计数器
        int pos = 0;

        for (Integer x: treeSet) {
            //将每个值对应的位置存放进map中
            map.put(x,++pos);
        }
        return map;

    }


    /*
        positon是二维数组，数组中每一个子数组是一个正方形 [1,3]代表边长为3的正方形沿着x = 1的轴落下来
     */
    public List<Integer> fs(int[][] positions){

        HashMap<Integer,Integer> map = index(positions);

        int N = map.size();

        SegmentTree segmentTree = new SegmentTree(N);

        int max = 0;
        //用于存放，每个方块掉下去的时候，当前的最大高度
        List<Integer> res = new ArrayList<>();

        for ( int[] pos: positions) {
            int L = map.get(pos[0]);
            int R = map.get(pos[0] + pos[1] - 1);

            int height = segmentTree.query(L,R,1,N,1);
            res.add(Math.max(max,height));
            segmentTree.update(L,R,height,1,N,1);
        }

        return res;

    }


}
