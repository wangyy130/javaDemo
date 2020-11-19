package com.wyy.javademo.suanfa.class11;

/**
 *
 * 范围上的尝试模型
 * 动态规划 ---从数组中拿卡片问题
 * 递归：分为先手和后手
 *
 * 先手总是能拿两种方案中最大的
 *
 * 后手总是被迫拿两种方案中的最小的
 *
 */
public class CardsInLine {

    public int getScroe(int[] arr){

        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0 , arr.length - 1),s(arr, 0 ,arr.length - 1));
    }

    //先手拿
    public int f(int[] arr, int L, int R){
        if(L == R){
            return arr[L];
        }

        //如果先手拿了L位置，那么就在 L+1 -- R位置上后手拿
        //如果先手拿了R位置，那么就在 L -- R - 1 位置上后手拿
        //因为是先手拿，所以要计算这两种方案中哪种自己拿的最多，选哪一种
        return  Math.max(arr[L] + s(arr, L + 1, R),arr[R] + s(arr, L , R -1));
    }


    //后手拿
    public int s(int[] arr,int L, int R){
        if(L == R){
            return 0;
        }

        //因为是后手拿，所以对方会给我留下相对于他来说最小的值
        //如果对方拿了L位置的牌，那我就在L + 1 --R上先手拿，
        //如果对方拿了R位置的牌，那我就在 L -- R -1 上先手拿
        //这两个哪个相对小，对方会留给我哪个
        return Math.min(f(arr,L + 1, R) , f(arr, L , R - 1));
    }


    /**
     * 动态规划的算法，要借助两张二维表
     * 两个变量，L，R
     * @param arr 给定的数字数组
     * @return
     */
    public int dpWay(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }


        int N = arr.length;
        int[][] f = new int[N][N]; //先手的缓存表
        int[][] s = new int[N][N]; //后手的缓存表

        //填充f先手二维表的对角线，当 L == R时，只允许先手拿一张
        for(int i = 0; i < N ; i++){
            f[i][i] = arr[i];
        }

        //接下来要填充对角线了 ，0列为头的对角线已经填充过了，现在从1开始进行填充
        for(int col = 1; col < N ; col++){

                int L = 0; //要遍历每一行来填充对角线
                int R = 1;
                while (L  < N && R < N){
                    f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);

                    s[L][R] = Math.min(f[L][R - 1],f[L + 1][R - 1]);
                    L++;
                    R++;
                }

        }

        return Math.max(f[0][N - 1], s[0][N - 1]);
    }
}
