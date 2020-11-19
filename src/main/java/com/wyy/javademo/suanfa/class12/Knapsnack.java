package com.wyy.javademo.suanfa.class12;

/**
 * 背包问题的动态规划
 */
public class Knapsnack {

    /*
        模拟填充动态规划的表，倒序填充，默认第N行的价值都为0


         横向标题 bag，剩余的容量
        纵向标题，第几号货物,从N开始遍历 倒序（正序也可以，结果和顺序无关）
            w/v	0	1	2	3	4	5	6	7	8	9	10	11

        _________________________________________________________
        0(3,5)	0	0	6	6	6	11	11	19	19	25	25	25
        1(2,6)	0	0	6	6	6	6	9	19	19	25	25	25
        2(4,3)	0	0	0	0	3	3	3	19	19	19	19	22
        3(7,19)	0	0	0	0	0	0	0	19	19	19	19	19
        4(N)	0	0	0	0	0	0	0	0	0	0	0	0
`
     */
    public int process(int[] w, int[] v, int bag){
        int[][] dp = new int[w.length][bag];

        //默认第N行都是0 ，直接从第N -1行进行遍历
        for(int index = w.length - 1 ; index >= 0 ; index--){
            for(int rest = 0; rest < bag; rest++){
                int p1 = dp[index + 1][rest];

                int p2 = -1;
                if(rest - w[index] > 0){
                    p2 = v[index] + dp[index][rest - w[index]];
                }

                dp[index][rest] = Math.max(p1,p2);
            }
        }

        return dp[0][bag];
    }

}


