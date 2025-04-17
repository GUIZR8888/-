package com.itheima.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        //1.队列名
        String queueName = "simple.queue";
        //2.消息
        String message = "hello,spring amqp!";
        //3.发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue() {
        //1.队列名
        String queueName = "work.queue";
        for (int i = 0; i <= 50; i++) {
            //2.消息
            String message = "hello,spring amqp_" + i;
            //3.发送消息
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }

    @Test
    public void testFanoutQueue() {
        //1.交换机名
        String exchangeName = "hmall.fanout";
        //2.消息
        String message = "hello,everyone!";
        //3.发送消息
        rabbitTemplate.convertAndSend(exchangeName, null, message);
    }

    @Test
    public void testDirectQueue() {
        //1.交换机名
        String exchangeName = "hmall.direct";
        //2.消息
        String message = "黄色：123456！";
        //3.发送消息
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }

    @Test
    public void testTopicQueue() {
        //1.交换机名
        String exchangeName = "hmall.topic";
        //2.消息
        String message = "今天天气不错！";
        //3.发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

}