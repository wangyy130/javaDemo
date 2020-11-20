package com.wyy.javademo.suanfa.class11;

/*
    N皇后问题 ，N 行，N 列，保证每一行都有皇后，且每个皇后之间都不能同行，同列，同斜线

    1、尝试： 遍历每行，每行只放一个皇后，肯定不会同行
        同时遍历每列，判断每个位置是否同列，同斜线
    2、优化： 利用位运算的方法进行优化
 */
public class NQueen {

    public int num1(int n){
        int[] record = new int[n];
        return process(record, 0 , n);
    }

    //将已经决策好的行放的皇后的位置放入record数组中，接下来要从i开始进行决策
    //每一行都是通过process过程来进行决策放在那一列的
    public int process(int[] record, int i, int n){
        if(i == n){
            return 1;
        }

        int res = 0;

        //遍历每一列
        for(int j = i; j < n; j++){
            if(isValid(record, i,j)){
                record[i] = j;
                //接着去下一行进行决策
                res += process(record, i + 1, n);
            }
        }

        return res;
    }

    //满足N皇后的条件是，不同列，且不同斜线，因为是按行遍历肯定已经不同行了
    public boolean isValid(int[] record, int i, int j){
        //去遍历之前的每一行，判断是否符合条件
        //这里也要判断第i行，所以， k < i
        for(int k = 0 ; k < i; k++){
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }



    //N 皇后问题第二种方法，利用位运算来进行优化
    //主要将问题分为三种限制
    //列限制，左斜线限制，右斜线限制，最后进行或运算可以求出全限制
    public int num2(int n){

        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (n << 1) - 1;

        return process2(limit,0, 0, 0);
    }

    /**
     *
     * @param limit 全量限制，全为0时，表示没有限制, limit 永远不会变，相当于n
     * @param colLimit 列限制，全为0时表示没有限制
     * @param leftLimit 左斜线限制，全为0表示没有限制
     * @param rightLimit 右斜线限制，全为0时表示没有限制
     * @return N皇后有多少种可能
     */
    public int process2(int limit , int colLimit, int leftLimit, int rightLimit){

        //baseCase 列限制，说明所有的列都已经放上皇后了，那么每一行也就都有了一个皇后了，
        //所以，这次的结果是满足条件的
        if(colLimit == limit){
            return 1;
        }

        /*
            colLimit | leftLimit | rightLimit  = 全限制，就是所有位置最后都不能放皇后的结果
            如果全为1，说明都不能放皇后了，如果都为0，说明每个位置都可以放皇后

            取反的结果： 所有能放皇后的都变成了1

            limit &  会将limit的限制，前面的为1的数最终变成0，仅剩 limit位

            所以，pos的含义就是 每个可以放皇后的位置都是1，不能放皇后的位置变成0
         */
        int pos = limit & (~(colLimit | leftLimit | rightLimit));
        //最右边可以放皇后的位置
        int mostRightOne = 0;

        int res = 0;

        while (pos !=0 ){
            mostRightOne = pos & (~pos + 1); //从最右边的列开始遍历

            pos = pos - mostRightOne; //减去最右边的1 ，就是逐个向左边遍历左边可以放皇后的位置

            res += process2(limit, colLimit | mostRightOne,
                    //左边对角线是当前列向左移动一位
                    (colLimit | mostRightOne) << 1,
                    //右边对角线是当前列向右移动一位
                    (colLimit | mostRightOne) >> 1);
        }

        return  res;
    }



}
