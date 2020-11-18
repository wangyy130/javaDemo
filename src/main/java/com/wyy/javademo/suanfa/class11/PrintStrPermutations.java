package com.wyy.javademo.suanfa.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、给定一个字符数组，打印这个字符数组的所有字符的全排列
 *
 * 利用数组交换的方式进行暴力递归
 *
 *
 * 2、打印全排列中去重之后的结果
 *
 *      可以用set
 *      也可以用分支限界法
 */
public class PrintStrPermutations {

    public List<String> Permutations(char[] strs){

         ArrayList<String> ans = new ArrayList<>();
         process(strs, 0, ans);
         return  ans;
    }

    //从index位置开始，[index....]的所有的数都可以来到index位置
    public void process(char[] strs, int index, ArrayList<String> ans){
        if(index == strs.length){
            ans.add(String.valueOf(strs));
            return;
        }

        for(int i = index; i < strs.length ; i++){
            swap(strs, index, i);
            process(strs, index + 1, ans);
            swap(strs,index,i);
        }

    }


    public void swap(char[] strs,int i, int j){
        char temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }

    /*
        采用分支限界法去重
        比如 a,a ,b  : 在进行0位置决策时，因为第一个a已经占据过0位置了，那么第二个a就不用再占用0位置了，这样可以去重

        从i位置开始，逐个去替换，每次替换位置时，只能跟i后面位置的数进行交换
     */
    public void process2(char[] chs, int i , ArrayList<String> ans){
        if(i == chs.length){
            ans.add(String.valueOf(chs));
            return;
        }
        //每个位置的字符，只要用过就标记为true，下次再来到同一位置替换时，相同的字符不再替换
        boolean[] visit = new boolean[26];
        for(int j = i; j < chs.length ; j++){
            if(!visit[(chs[j] - 'a')]){
                visit[chs[j] - 'a'] = true;
                swap(chs,i, j);
                process2(chs,i + 1, ans);
                swap(chs,i,j);
            }
        }
    }



}
