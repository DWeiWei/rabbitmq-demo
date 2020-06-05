package com.rabbitmq.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TestSender {
    protected static Logger logger = LoggerFactory.getLogger(TestSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param exchange
     * @param routing
     * @param msg
     */
    public void sendMessage(String exchange, String routing, Object msg) {
        logger.info("发送的消息：{}", msg);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange, routing, msg, correlationId);
    }
}
