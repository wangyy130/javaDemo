package com.wyy.javademo.suanfa.class12;

/**
 * 关于洗咖啡杯问题的动态规划
 *
 * 对于每一个咖啡杯都有两种可能性
 * 1、洗干净
 * 2、挥发干净
 * 求二者结果的最小值即可
 */
public class Coffe {


    /**
     * 返回洗完所有咖啡杯的最少时间点
     * @param drink 每一杯咖啡喝完的时间点，每一杯咖啡喝完的时间点都是按顺序的
     * @param index 当前咖啡的索引 ，index参数可变
     * @param a 洗完一杯咖啡的时间
     * @param b 挥发干净一杯咖啡的时间
     * @param washline 当前咖啡机可以洗咖啡的时间节点，
     *                 参数可变，根据业务，最大的时间点为所有杯子都洗干净的时间点为最大的washline
     * @return
     */
    public int process(int[] drink, int index, int a, int b, int washline){
       //如果挥发时间比洗干净的时间短，那么直接喝完挥发就可以了
        if(a < b){
            return drink[drink.length - 1] + b;
        }

        //如果只剩最后一个杯子了
        if(index == drink.length - 1){
            return Math.min(Math.max(drink[index] + a,washline),drink[index] + b);
        }

        //可能性1 ，当前杯子是洗干净的
        //当前杯子洗完来到的时间点
        int curWashLine = Math.max(drink[index] , washline ) + a;
        //洗完剩余的杯子来到的时间点
        int nextLine = process(drink,index + 1,a, b,  curWashLine);

        int p1 = Math.max(curWashLine,nextLine);

        //可能性2， 当前杯子挥发干净的
        int dry = drink[index] + b;
        int next2 = process(drink, index + 1, a, b, dry);
        int p2 = Math.max(dry,next2);

        return Math.min(p1,p2);
    }




    /*
        递归方法：
        难点在于求两个可变参数中，一个是washline
        washline要根据业务来指定范围
        最大值是当所有咖啡杯都洗干净时的时间点
     */
    public int dpWay(int[] drink, int a ,int b){
        if(a < b){
            return drink[drink.length - 1] + b;
        }

        //求limit
        int limit = 0;
        for(int i = 0; i < drink.length; i++){
            limit = Math.max(limit, drink[i] ) + a;
        }

        int N = drink.length;

        //开始进行动态规划
        int[][] dp = new int[N - 1][limit + 1];

        //要填充N - 1行
        for(int washline = 0; washline < limit; washline++){
            dp[N - 1][washline] = Math.min(Math.max(drink[N - 1],dp[N - 1][washline] + a),drink[N - 1] + b);
        }

        //开始填充其他行
        for(int index = N - 2; index >= 0 ; index--){
            for (int washline = 0; washline < limit; washline++){
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washline, drink[index]) + a;
                if (wash <= limit) {
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }
                int p2 = Math.max(drink[index] + b, dp[index + 1][washline]);
                dp[index][washline] = Math.min(p1, p2);
            }
        }

        return dp[0][0];

    }
}
