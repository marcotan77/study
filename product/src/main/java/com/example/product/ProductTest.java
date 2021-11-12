package com.example.product;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/7/10 16:28
 **/
public class ProductTest {
    public static void main(String[] args) throws MQClientException {
        final DefaultMQProducer product = new DefaultMQProducer("test_product");
        product.setNamesrvAddr("192.168.110.102:9876");
        product.start();

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        try {
                            Message msg = new Message("TopicTest","TagA",("Test-->"+Thread.currentThread().getName()).getBytes(RemotingHelper.DEFAULT_CHARSET));
                            SendResult send = product.send(msg);
                            System.out.println("发送消息结果"+send);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (MQClientException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (RemotingException e) {
                            e.printStackTrace();
                        } catch (MQBrokerException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
