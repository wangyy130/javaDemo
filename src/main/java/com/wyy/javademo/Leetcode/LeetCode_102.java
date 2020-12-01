package com.wyy.javademo.Leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class LeetCode_102 {

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode,Integer> map = new HashMap<>();
        queue.add(root);
        map.put(root,0);

        int curLevel = 0;

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        while(!queue.isEmpty()){

            TreeNode cur = queue.poll();

            if(map.get(cur) == curLevel){
                list.add(cur.val);
            }else{
                res.add(list);
                list = new ArrayList<>();
                list.add(cur.val);
                curLevel++;
            }
            if(cur.left != null){
                queue.add(cur.left);
                map.put(cur.left, curLevel + 1);
            }

            if(cur.right != null){
                queue.add(cur.right);
                map.put(cur.right, curLevel + 1);
            }
        }

        res.add(list);
        return res;

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(levelOrder(root).toArray().toString());
    }

}
