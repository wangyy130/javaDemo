package com.wyy.javademo.suanfa.class08;


import java.util.List;

/**
 * 派对的最大快乐值问题
 *
 * 多叉树
 *
 * 多路递归
 *
 * 规则： 公司员工，每个员工都有一个唯一的直接上级，除了基层员工以外，每个员工都有1个或多个直接下级
 *
 * 参加派对的规则：
 *
 * 1、如果员工x来了，那么他的所有直接下级都不能来，每个员工有自己的快乐值
 * 2、求到场人员的最大快乐值
 */
public class MaxHappy {


    //员工信息
    public class Employee{
        public int happy;
        public List<Employee> nexts;
    }


    //返回信息，根据某个员工x来或不来进行划分，如果来最大值，不来最大值

    public class Info{
        //员工来的时候，最大happy
        public int yes;
        //员工不来的时候，最大happy
        public int no;

        public Info(int yes, int no){
            this.yes = yes;
            this.no = no;
        }
    }


    public Info maxHappy(Employee employee){
        //以nexts下属员工是否为空来作为basecase

        if(employee.nexts == null){
            return new Info(employee.happy,0);
        }

        int yes = employee.happy;
        int no = 0;

        for(Employee e : employee.nexts){
            Info nextInfo = maxHappy(e);
            //如果x来，那么所有下属都不来，就加上所有子树不来的时候的happy值
            yes += nextInfo.no;
            //如果x不来，那么下属可能来，也可能不来，加上来或不来的最大值
            no += Math.max(nextInfo.yes , nextInfo.no);
        }


        return new Info(yes , no);
    }



}
