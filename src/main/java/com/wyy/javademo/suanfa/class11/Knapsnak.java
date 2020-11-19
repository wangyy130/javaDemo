package com.wyy.javademo.suanfa.class11;

/**
 * 动态规划： 0 1背包问题
 *
 * 递归思路：1、每个货物放或不放，通过计算已经存放进背包的总重量不大于背包的总重量，获取较大的价值
 * 2、每个货物放或不放，通过计算剩余背包的重量不小于0 ，获取较大的价值
 *
 *
 */
public class Knapsnak {

    /**
     *
     * @param w 每个货物对应的重量的数组
     * @param v 每个货物对应的价值数组
     * @param bag 背包的总总量
     * @return 返回背包中放的货物的最大的价值
     */
    public int maxValue(int[] w, int[] v, int bag){
        return process(w,v,bag,0,0);
    }

    // alwayW是放进背包内的总的重量
    //这里的basecase有两个，主要用来判断每决定一个货物，放或者不放，是否违规，是否超过背包的重量
    public int process(int[] w, int[] v, int bag, int index, int alwayW){
        //basecase1,违规了，超过了背包的总重量
        if(alwayW > bag){
            return  -1;
        }
        //basecase2，返回0 代表当前数组没有货物可以放了，但是没有违规，价值为0
        if(index == w.length){
            return 0;
        }

        //不放当前index货物，剩余货物的总价值
        int p1 = process(w,v,bag,index + 1, alwayW);

        //放当前货物
        int p2Next = -1;
        //如果放当前货物，剩余货物的总价值
        p2Next = process(w,v, bag, index + 1, alwayW + w[index]);

        int p2 = -1;
        if(p2Next != -1){
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }


    public int maxValue2(int[] w, int[] v, int bag){
        return  process2(w,v,0, bag);
    }

    //rest 背包的剩余重量
    //当前index对应的背包所产生的最大价值
    public int process2(int[] w, int[] v, int index, int rest){
        //如果剩余总量小于0，违规了
        if(rest < 0){
            return  -1;
        }
        //如果index已经到了最后了，没有违规，但是价值为0
        //就是不能要他了，那么他的价值一定为0
        if(index == w.length){
            return 0;
        }

        int p1 = process2(w,v,index + 1, rest );

        int p2Next = process2(w,v,index+1, rest - w[index]);

        int p2 = -1;

        if(p2Next != -1){
            p2 = v[index] + p2Next;
        }

        return Math.max(p1,p2);
    }
}
