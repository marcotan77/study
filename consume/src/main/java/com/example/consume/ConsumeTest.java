package com.example.consume;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/7/10 16:36
 **/
public class ConsumeTest {
    public static void main(String[] args) throws MQClientException {
        final DefaultMQPushConsumer consume = new DefaultMQPushConsumer("test_consume");
        consume.setNamesrvAddr("192.168.110.102:9876");
        consume.subscribe("TopicTest", "*");

        consume.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list) {
                    String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                    System.out.println(body);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consume.start();

    }
}
