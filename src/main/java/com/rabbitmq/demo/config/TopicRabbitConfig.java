package com.rabbitmq.demo.config;

import com.rabbitmq.demo.constant.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TopicRabbitConfig {

    public static final String TEST_ROUTING = "test.topic";

    /**
     * 队列名
     *
     * @return
     */
    @Bean
    @Qualifier(RabbitMQConstant.TEST_TOPIC_QUEUE)
    public Queue testTopicQueue() {
        return new Queue(RabbitMQConstant.TEST_TOPIC_QUEUE, true);
    }

    /**
     * topic交换机
     *
     * @return
     */
    @Bean
    @Qualifier(RabbitMQConstant.TEST_TOPIC_EXCHANGE)
    public TopicExchange testTopicExchange() {
        return new TopicExchange(RabbitMQConstant.TEST_TOPIC_EXCHANGE);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding topicBinding(@Qualifier(RabbitMQConstant.TEST_TOPIC_EXCHANGE) TopicExchange exchange,
                                @Qualifier(RabbitMQConstant.TEST_TOPIC_QUEUE) Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(TEST_ROUTING);
    }

}
