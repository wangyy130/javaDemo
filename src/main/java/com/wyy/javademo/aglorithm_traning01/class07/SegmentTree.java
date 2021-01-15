package com.wyy.javademo.aglorithm_traning01.class07;

/**
 * 线段树
 * 解决的问题：区间修改，查询，增加
 * 保证所有操作时间复杂度O（logN）
 * L...R 任务左边界，右边界
 *
 * l...r 来到真正区间的左边界，右边界
 *
 * rt，所在数组中的位置
 *
 */
public  class SegmentTree {

    //数组的最大长度
    public static int MAXX;

    public static int[] arr; //为了方便将源数组copy到arr中，下标从1开始

    public static int[] sum; //用于存放原数组中每个区间的和

    public static int[] addLazy; //用于区间添加时，懒添加的数组

    public static boolean[] update; //用于区间修改时，懒更新的标记

    public static int[] change; //用于区间修改时，懒更新的值


    //构造方法，去初始化数组和分配4N的空间来存放累加和，懒添加，懒更新等
    public SegmentTree(int[] orgin){
        MAXX = orgin.length + 1;
        arr = new int[MAXX];
        for(int i = 0; i < orgin.length; i++){
            arr[i + 1] = orgin[i];
        }

        sum = new int[ MAXX << 2 ];
        addLazy = new int[ MAXX << 2 ];
        update = new boolean[ MAXX << 2 ];
        change = new int[ MAXX << 2 ];
    }


    //构建初始的sum数组
    //在初始化1-N范围 l = 1, r = N进行构建sum
    public  void build(int l, int r, int rt){
        if(l == r){
            sum[rt] = arr[l];
        }

        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

    //汇总sum
    public void pushUp(int rt){
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    //区间添加
    //L .. R 任务左边界，右边界 1--1000，范围上每个数新增10
    //l...r 来到的范围左右边界 1---1024,1--512,513--1024
    //C 新增的数
    public void add(int L, int R, int C, int l, int r, int rt){
        //判断是否全包
        if(l >= L && r <= R){
            addLazy[rt] += C;
            sum[rt] += (r - l + 1) * C;
            return;
        }

        int mid = (l + r) >> 2;
        //如果没有全包，需要先将rt之前的lazy进行下发
        pushDown(rt,mid - l + 1, r - mid);

        //下发完之前的任务了，需要开始分发当前的任务了

        //判断左边是否需要接收任务
        if(L <= mid){
            add(L, R, C, l, mid, rt << 1);
        }
        //判断右边是否需要接收任务
        if(R > mid){
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }

        pushUp(rt);
    }

    //区间懒更新
    public void update(int L, int R, int C, int l, int r, int rt){
        if(l >= L && r <= R ){
            update[rt] = true;
            change[rt] = C;
            sum[rt] = ( r - l + 1 ) * C;
            addLazy[rt] = 0; //更新，会让所有的懒新增都无效
            return;
        }

        int mid = (l + r) >> 1;

        pushDown(rt, mid - l + 1, r - mid);

        if(L <= mid){
            update(L, R, C, l, mid, rt << 1);
        }

        if(R > mid){
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }

        pushUp(rt);
    }

    //查询区间总和
    public long query(int L, int R, int l, int r, int rt){
        if(l >= L && r <= R){
            return sum[rt];
        }

        int mid = (l + r) >> 1;

        pushDown(rt, mid - l + 1, r - mid);

        int ans = 0;

        if(L <= mid){
            ans += query(L, R, l, mid, rt << 1);
        }

        if(R > mid){
            ans += query(L, R, mid + 1, r - mid, rt << 1 | 1);
        }

        return ans;
    }


    //ln 表示左边节点的个数，rn表示右边节点的个数
    public void pushDown(int rt, int ln, int rn){
        //先判断是否有懒更新
        if(update[rt]){
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;


            change[rt << 1] =  change[rt];
            change[rt << 1 | 1] = change[rt];

            //父节点懒更新向下发，子节点的所有懒增加全部失效变为0
            addLazy[rt << 1] = 0;
            addLazy[rt << 1 | 1] = 0;

            sum[rt << 1] = ln * change[rt];
            sum[rt << 1 | 1] = rn * change[rt];
        }
        //再判断是否有懒新增
        if(addLazy[rt] != 0){
            addLazy[rt << 1] = addLazy[rt];
            addLazy[rt << 1 | 1] = addLazy[rt];

            sum[rt << 1] += addLazy[rt] * ln;
            sum[rt << 1 | 1] += addLazy[rt] * rn;

            addLazy[rt] = 0;
        }

    }

}
