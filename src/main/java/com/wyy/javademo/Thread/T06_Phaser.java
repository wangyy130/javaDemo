package com.wyy.javademo.Thread;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T06_Phaser {

    static Random r = new Random();
    static MarriagePhaser marriagePhaser = new MarriagePhaser();

    static class People extends Thread{

        public String name;

        People(String name){
            this.name = name;
        }


        static void milliSleep(int milli) {
            try {
                TimeUnit.MILLISECONDS.sleep(milli);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        public void arrive(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场! \n",name);
            marriagePhaser.arriveAndAwaitAdvance(); //等待所有人都完成
        }


        public void eat(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完饭了! \n",name);
            marriagePhaser.arriveAndAwaitAdvance();
        }


        public void leave(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开了！ \n",name);
            marriagePhaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            milliSleep(r.nextInt(1000));

            if("新郎".equals(name) || "新娘".equals(name)){
                System.out.printf("%s 抱抱 \n",name);
                marriagePhaser.arriveAndAwaitAdvance();
            }else {
                marriagePhaser.arriveAndDeregister(); //其他人不参与此过程
            }
        }


        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();
        }
    }





    static class MarriagePhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0 : //第一个阶段完成
                    System.out.println("所有人都到了 "  + registeredParties);
                    return false;
                case 1 :
                    System.out.println("所有人都吃完饭了 " + registeredParties);
                    return false;
                case 2 :
                    System.out.println("所有人都离开了 " + registeredParties);
                    return false;

                case 3 :
                    System.out.println("新郎新娘入洞房了" + registeredParties);
                    return  false;
                default:
                    return true;
            }
        }
    }


    public static void main(String[] args) {
        marriagePhaser.bulkRegister(7); //先注册七个人

        for (int i = 0 ; i < 5; i++){
            new People("p"+i).start();
        }

        new People("新娘").start();
        new People("新郎").start();
    }
}
