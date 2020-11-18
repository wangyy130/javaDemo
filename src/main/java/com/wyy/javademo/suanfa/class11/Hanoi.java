package com.wyy.javademo.suanfa.class11;

/**
 * 汉诺塔问题
 */
public class Hanoi {

    public static void main(String[] args) {
        int n = 3;
        leftToRight(n);
        System.out.println("================");
        hanoi2(3,"left","right","mid");
    }


    public static  void leftToRight(int n){
        if(n == 1){
            System.out.println("move "+ n + " from left to right");
            return;
        }
        leftToMid(n -1 );
        System.out.println("move " + n + " from left to right");
        midToRight(n - 1);
    }


    public static  void leftToMid(int n){
        if(n == 1){
            System.out.println("move "+ n + " from left to mid");
            return;
        }

        leftToRight(n - 1);
        System.out.println("move " + n +" from left to mid");
        rightToMid( n -  1);

    }


    public static void midToRight(int n){
        if(n == 1){
            System.out.println("move " + n +" from mid to right");
            return;
        }
        midToLeft( n - 1);
        System.out.println("move "+ n + " from mid to right");
        leftToRight( n - 1);

    }


    public static  void rightToMid(int n){
        if(n == 1){
            System.out.println("move " + n +" from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move " + n + " from right to mid");
        leftToMid( n  -1 );

    }


    public static void rightToLeft(int n){
        if(n == 1){
            System.out.println("move " + n + " from right to left");
            return;
        }

        rightToMid( n  - 1);
        System.out.println("move " + n + " from right to left");
        midToLeft( n - 1);
    }


    public static  void midToLeft(int n){
        if(n == 1){
            System.out.println("move " + n + " from mid to left");
            return;
        }
        midToRight( n - 1);
        System.out.println("move "+ n + " from mid to left");
        rightToLeft( n  -  1);
    }



    public static void hanoi2(int n , String from, String to, String other){
        if(n == 1){
            System.out.println("move "+ n + " from " + from + "  to " + to );
            return;
        }

        hanoi2(n - 1, from , other, to);
        System.out.println("move "+ n + " from " + from + "  to " + to );
        hanoi2(n - 1, other, to, from);

    }

}
