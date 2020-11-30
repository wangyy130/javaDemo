package com.wyy.javademo.Leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode_767 {
    public static String reorganizeString(String S) {
        if(S == null || S.length() == 0){
            return "";
        }

        int[] map = new int[26];
        char[] arr = S.toCharArray();
        int N = arr.length;
        int max= Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            map[arr[i] - 'a']++;
            max = Math.max(max, map[arr[i] - 'a']);
        }

        System.out.println("max = "+ max);
        if((N % 2 == 0 && max > N / 2) || (N % 2 == 1 && max > N / 2 + 1)){
            return "";
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>(){
            public int compare(Character letter1, Character letter2){
                return map[letter2 - 'a']  - map[letter1 - 'a'];
            }
        });


        for(int i = 0; i < map.length; i++){
            if(map[i] != 0){
                char c = (char)(i + 'a');
                queue.add(c);
            }
        }

        String res = "";
        while(queue.size() > 1){
            char cur = queue.poll();
            char next = queue.poll();

            res += cur;
            res += next;


            if((--map[cur - 'a']) > 0){
                queue.add(cur);
            }

            if((--map[next - 'a']) > 0){
                queue.add(next);
            }


        }

        if(queue.size() > 0){
            res += queue.poll();
        }

        return res;

    }


    public static void main(String[] args) {
        System.out.println(reorganizeString("aaab"));
    }
}
