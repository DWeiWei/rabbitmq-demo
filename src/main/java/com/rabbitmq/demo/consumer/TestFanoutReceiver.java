package com.rabbitmq.demo.consumer;


import com.rabbitmq.client.Channel;
import com.rabbitmq.demo.constant.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = RabbitMQConstant.TEST_FANOUT_QUEUE)
public class TestFanoutReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void onMessage(String content, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            logger.info("fanout类型接收处理的消息：{},{}", content,tag);
            channel.basicAck(tag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
