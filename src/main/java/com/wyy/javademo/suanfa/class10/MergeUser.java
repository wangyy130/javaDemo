package com.wyy.javademo.suanfa.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeUser {

    static class User{
        public int a;
        public int b;
        public int c;

        User(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    //如果两个user，a一样，或者b一样，或者c一样，那么认为是一个人
    //求一个数组中的合并之后user一共几个人
    public static int mergeUsers(List<User> users){
        UnionSet<User> unionSet = new UnionSet<>(users);
        Map<Integer,User> mapA = new HashMap<>();
        Map<Integer,User> mapB = new HashMap<>();
        Map<Integer,User> mapC = new HashMap<>();

        for (User user:users) {
            if(mapA.containsKey(user.a)){
                unionSet.union(user,mapA.get(user.a));
            }else{
                mapA.put(user.a,user);
            }

            if(mapB.containsKey(user.b)){
                unionSet.union(user,mapB.get(user.b));
            }else{
                mapB.put(user.b,user);
            }

            if(mapC.containsKey(user.c)){
                unionSet.union(user,mapC.get(user.c));
            }else{
                mapC.put(user.c,user);
            }
        }

        return unionSet.getNum();

    }
}
