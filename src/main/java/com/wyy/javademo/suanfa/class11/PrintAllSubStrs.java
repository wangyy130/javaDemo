package com.wyy.javademo.suanfa.class11;

import java.util.ArrayList;

/**
 * 打印字符串的所有子序列
 *
 * 子串和子序列的区别：
 * 1、子串： 必须从左往右连续的
 * 2、子序列：可以不连续
 *
 *
 *这个类似于二叉树的递归套路，每个位置的字符 要  或 不要
 *
 *
 * 如果打印所有不重复的子序列，可以用set代替list
 *
 */
public class PrintAllSubStrs {


    public static  void  printAllSubSequence(String strs){
        char[] chars = strs.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        process(chars, 0 ,"", ans);
    }


    public static void process(char[] strs, int index,String path, ArrayList<String> ans){
        if(index == strs.length){
            ans.add(path);
            return;
        }else {

            //如果当前的字符不要
            process(strs,index + 1,path,ans);

            //如果当前位置的字符要的话
            path = path + String.valueOf(strs[index]);
            process(strs,index+1, path , ans);
        }

    }
}
