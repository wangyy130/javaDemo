package com.wyy.javademo.suanfa.class09;


import java.util.*;

/**
 * 贪心算法解决最低字典排序字符串
 */
public class lowestStr {

    public String getLowestString(String[] sts){
        if(sts == null || sts.length ==0){
            return null;
        }

        List<String> all = new ArrayList<>();

        Set<Integer> used = new HashSet<>();

        process(sts,all,"",used);
        String lowest = all.get(0);

        for (String s : all) {
            if (s.compareTo(lowest) < 0) {
                lowest = s;
            }
        }

        return lowest;

    }

    /**
     *假设只有3个字符串，来做注释，字符串的全排列问题
     * @param strs 给定的字符串数组
     * @param all 所有可能的拼接成的字符串结果
     * @param path 每次拼接的结果为一个path，所有字符串拼接的一个结果为一个path
     * @param used 每次轮询时已经用过的字符串数组的下标
     */
    public void process(String[] strs,List<String> all ,String path, Set<Integer> used){

        if(used.size() == strs.length){
            //如果当前这次轮询中已经用过的字符串数 = 数组的长度，已经完成了一次path的拼接，将他加入all中
            all.add(path);
        }else{
            //从第0个位置开始深度遍历
            //每次进入process时，i总是从0开始遍历
            //当继续第二次进入这里时，i还是从0开始遍历
            //当继续第三次进入，i还是从0开始，i每次都从0开始遍历
            //每次开始时会记录i的值，当从process返回时，会接着上次的i值进行向下遍历
            for(int i = 0 ; i < strs.length  ; i++){
                //如果used中没有使用过i
                //第二次进入时，因为used中已经包含了0了，所以，从1开始
                //当第三次进入时，因为used已经包含了0,1,所以这次从2开始
                //当到三次出来后，used = 0,1
                if(!used.contains(i)){
                    //在used中加入i表示i已经使用过了
                    //第二次进入 used = 0,1
                    //第三次进入used =0,1,2
                    used.add(i);
                    path += strs[i]; //拼接i字符串
                    process(strs , all, path , used);
                    //当第三次返回时，第一次走到这里，这个时候i = 2，移除2，used = 0,1，继续在循环里面遍历
                    //当第四次返回时，会将1也移除掉，此时i=1，继续当i=2时再遍历，但是当i=2时，used = 0,所以继续遍历 0,2,1
                    used.remove(i);
                }
            }
        }

    }


    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return  (o1 + o2).compareTo( o2 + o1) ;
        }
    }



    public static String getLowest(String[] strs){

        if(strs == null || strs.length ==0){
            return null;
        }
        Arrays.sort(strs,new MyComparator());
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }

        return res.toString();
    }



}
