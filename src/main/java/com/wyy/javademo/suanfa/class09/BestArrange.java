package com.wyy.javademo.suanfa.class09;


import java.util.Arrays;
import java.util.Comparator;

//会议安排问题
public class BestArrange {

    static class Program{
        public int start;
        public int end;

        Program(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    //暴力递归的方法
    public int bestArrage(Program[] programs){
        if(programs == null || programs.length == 0){
            return 0;
        }
        return process(programs,0,0);
    }


    /**
     *
     * @param programs 还剩的会议列表
     * @param done 已经安排的会议数
     * @param timeline 目前来到的时间点
     * @return 返回能安排的最多会议数量
     */
    public int process(Program[] programs,int done, int timeline){
        if(programs.length == 0){ //baseCase
            return done;
        }else{

            int max = done;
            //先遍历，从第0号会议开始安排，每次进入process都从0开始遍历
            //进入时会记录i值，每次出process时，会接着进入的i值继续遍历，完成全排列
            for(int i = 0 ; i < programs.length; i++){
                if(programs[i].start >= timeline){
                    //安排第i号会议，并且将i号会议从会议列表中除去
                    Program[] nexts = copyButExcept(programs,i);
                    //安排剩下的会议
                    //每次走到这里，只要是第一层循环走到这里都能计算出来安排了多少个会议，跟之前的max求最大值就可以了
                    max = Math.max(process(nexts,++done,programs[i].end),max);

                }

            }
            return  max;
        }
    }

    public Program[] copyButExcept(Program[] programs,int i){
        Program[] arr = new Program[programs.length - 1];
        for (int j = 0 ; j < programs.length; j++){
            if(j != i){
                arr[j] = programs[j];
            }else{
                j++;
            }
        }
        return  arr;
    }


    //贪心算法
    public int bestArrange2(Program[] programs){
        Arrays.sort(programs,new MyComparator());
        int timeline = 0 ; //当前的时间点
        int result = 0; //安排的会议数量
        for(int i = 0; i < programs.length ; i++){
            //当前会议开始时间晚于当前时间点时，可以安排当前会议
            if(programs[i].start >= timeline){
                result++;
                timeline = programs[i].end;
            }
        }

        return result;
    }


    //自定义比较器，哪个会议结束早，排在前面
    class MyComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }
}

