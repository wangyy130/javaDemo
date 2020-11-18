package com.wyy.javademo.MQ;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

public class Consumer {
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("gp");
        consumer.subscribe("","");
        consumer.start();
    }
}
