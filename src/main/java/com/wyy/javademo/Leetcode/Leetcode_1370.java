package com.wyy.javademo.Leetcode;

/**
 * 将字符串str中按字符大小，从小到大拼，再从大到小拼，直到所有的字符用完为止
 */
public class Leetcode_1370 {

    class Solution {
        public String sortString(String s) {
            char[] strs = s.toCharArray();

            //字符串的字符计数器
            int[] map = new int[26];
            for(int i = 0; i < strs.length; i++){
                map[strs[i] - 'a']++;
            }

            StringBuilder sb = new StringBuilder();

            while(sb.length() < strs.length){
                process(map,sb);
            }
            return sb.toString();

        }


        public void process(int[] map,StringBuilder sb){
            for(int i = 0; i <  26; i++){
                if(map[i] != 0){
                    char zifu=(char) (i + 'a');
                    sb.append(zifu);
                    map[i]--;
                }
            }

            for(int i = 25; i >= 0; i--){
                if(map[i] != 0){
                    char zifu=(char) (i + 'a');
                    sb.append(zifu);
                    map[i]--;
                }
            }


        }
    }
}
