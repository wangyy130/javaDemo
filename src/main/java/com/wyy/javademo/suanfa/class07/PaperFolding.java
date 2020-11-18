package com.wyy.javademo.suanfa.class07;


/**
 * 纸条折叠问题
 *
 * 这个问题的basecase设计非常值得推敲
 *
 */
public class PaperFolding {


    //打印纸折叠的标记 是二叉树的中序排序问题
    //N 代表深度，共折叠了几次，down代表向上还是向下，每次折叠，上面的都是凹，下面的都是凸
    public void printPaperFlag(int N, int i, boolean down){
        //basecase非常值得推敲
        if(i > N){
            return ;
        }
        printPaperFlag(N,i + 1,true);
        System.out.print(!down ? "凸" : "凹");
        printPaperFlag(N,i + 1, false);
    }


}
