package com.wyy.javademo.suanfa.class12;

import org.apache.commons.collections.set.PredicatedSet;

/**
 * 货币兑换的问题
 * 思路：如果兑换1000元，数组中有 [10,20,50]
 *
 * 1、从第一张开始遍历
 *      如果用0张10元 那么后序 去获取子问题的解 f(0, 1000)
 *      如果用1张10元，后序 f(1, 990)
 *      ...
 *      不能超过100张，因为100 * 10 == 1000
 *
 * 2、从第二张开始
 *      如果用0张20元 后序 f(0, 1000)
 *      如果用1张20元，后序 f(1, 980)
 *      ...
 *      不能超过 50张 50 * 20 == 1000
 *
 * 3、第三张开始直到数组中所有的面值都遍历完成
 *
 */
public class CoinsWay {

    //暴力递归的方法
    public int ways1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim <=0){
            return 0;
        }
        return process(arr,0,aim);
    }


    /**
     * 求兑换货币一共多少中方法，暴力递归
     * @param arr 有多少中面值的货币，都存放在arr中
     * @param rest 兑换的目标货币
     * @param index 从第index开始进行尝试
     * @return 返回可以兑换的方法种数
     */
    public int process(int[] arr, int index,int rest){
        //baseCase
        if(index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        //index位置的货币从0张开始尝试，直到金额 = rest
        for(int zhang = 0; zhang * arr[index] <= rest; zhang++){
            ways += process(arr,index + 1, rest - arr[index] * zhang);
        }

        return ways;
    }


    //傻缓存的方法
    public int waysCache(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim <=0){
            return 0;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];

        //要初始化dp表,下面有判断rest是否为0，所以初始化的时候不能是0
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j< aim ; j++){
                dp[i][j] = -1;
            }
        }
        return  process2(arr, 0, aim, dp);
    }

    //可变参数 index 和 rest，zhang属于需要枚举的值
    public int process2(int[] arr, int index, int rest, int[][] dp){

        //先判断是否计算过当前的格子的数据
        if(dp[index][rest] != -1){
            return dp[index][rest];
        }

        if(index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int ways = 0;
        //index位置的货币从0张开始尝试，直到金额 = rest
        for(int zhang = 0; zhang * arr[index] <= rest; zhang++){
            ways += process(arr,index + 1, rest - arr[index] * zhang);
        }

        dp[index][rest] =  ways;
        return ways;
    }


    //经典动态规划法
    public int ways3(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim <=0){
            return 0;
        }

        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for(int index = N - 1; index >= 0 ; index--){
            for(int rest = 0; rest < aim; rest++){

                //这里枚举可以进行优化
                for(int zhang = 0; zhang * arr[index] <= rest; zhang++){
                    dp[index][rest] = dp[index + 1][rest - arr[index]];
                }

            }
        }

        return dp[0][aim];
    }

    //优化后的动态规划
    /*
        面值 [1,2,5]

        总共aim = 10 元

        index/rest	0元	1元	2元	3元	4元	5元	6元	7元 8元 9元 10元
            0(1) 	1	1	2	2	3	4	5	6	7	8	10
            1(2) 	1	0	1	0	1	1	1	1	1	1	2
            2(5) 	1	0	0	0	0	1	0	0	0	0	1
        3（N +1）	1	0	0	0	0	0	0	0	0	0	0

     */
    public int ways4(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim <=0){
            return 0;
        }

        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for(int index = N - 1; index >= 0; index--){
            for(int rest = 0; rest < aim; rest++){
                dp[index][rest] = dp[index + 1][rest];
                if(rest - arr[index] >=0 ){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }

        return dp[0][aim];
    }

}
