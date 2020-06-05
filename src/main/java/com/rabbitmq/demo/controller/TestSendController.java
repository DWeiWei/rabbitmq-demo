package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.config.DirectRabbitConfig;
import com.rabbitmq.demo.config.TopicRabbitConfig;
import com.rabbitmq.demo.producer.TestSender;
import com.rabbitmq.demo.constant.RabbitMQConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class TestSendController {

    @Autowired
    private TestSender testSender;

    @PostMapping
    public void send() {
        testSender.sendMessage(RabbitMQConstant.TEST_DIRECT_EXCHANGE,
                DirectRabbitConfig.DIRECT_ROUTING,
                "hello direct");
        testSender.sendMessage(RabbitMQConstant.TEST_TOPIC_EXCHANGE,
                TopicRabbitConfig.TEST_ROUTING,
                "hello topic");
        testSender.sendMessage(RabbitMQConstant.TEST_FANOUT_EXCHANGE,
                "",
                "hello fanout");
    }
}
