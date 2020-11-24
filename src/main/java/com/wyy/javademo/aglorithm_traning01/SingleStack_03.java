package com.wyy.javademo.aglorithm_traning01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈问题
 * 给定一个数组，求数组中任意一个位置的左边比其小并且最近的数，和右边比其小并且最近的数、
 *
 * 借助栈来实现
 * 从栈底到栈顶，代表值要从小到大，存放的是数组中的index值、
 *
 * 如果数组中存在相同的值，则要将相同的值放在一个数组中一起压入栈中
 */
public class SingleStack_03 {

    /**
     * 求比数组中每个位置数小，且离当前位置最近的左边和右边的位置
     * @param arr 给定的数组
     * @return
     * 返回：
     * [
     *      [-1,1] //0位置左边和右边的位置
     *      [ 1, 2] //1 位置左边和右边
     * ]
     */
    public static  int[][] getNearLess(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }

        int[][] res = new int[arr.length][2];
        //栈底 -> 栈顶 从小到大 ，必须是大压小
        Stack<List<Integer>> stack = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            //如果栈顶的元素代表值比当前元素大
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){

                List<Integer> popIL = stack.pop(); //弹出栈顶元素

                for(Integer index : popIL){
                    Integer leftNearLess = stack.isEmpty() ? -1 : stack.peek().get(0);
                    res[index][0] = leftNearLess;
                    res[index][1] = i;
                }

            }

            //当 = 时
            if(stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else{
                //栈为空
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }


        //当遍历完arr中的所有元素时，此时要判断栈是否为空，如果不为空，还要继续结算栈中剩余的元素
        while (!stack.isEmpty()){
            List<Integer> list = stack.pop();

            for(Integer index  : list){
                Integer leftNearLess = stack.isEmpty() ? -1 : stack.peek().get(0);
                res[index][0] = leftNearLess;
                res[index][1] = -1;
            }
        }

        return  res;
    }
}
