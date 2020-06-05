package com.rabbitmq.demo.config;

import com.rabbitmq.demo.constant.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DirectRabbitConfig {

    public static final String DIRECT_ROUTING = "test.direct";

    /**
     * 队列名
     *
     * @return
     */
    @Bean
    @Qualifier(RabbitMQConstant.TEST_DIRECT_QUEUE)
    public Queue testDirectQueue() {
        return new Queue(RabbitMQConstant.TEST_DIRECT_QUEUE, true);
    }

    /**
     * direct交换机
     *
     * @return
     */
    @Bean
    @Qualifier(RabbitMQConstant.TEST_DIRECT_EXCHANGE)
    public DirectExchange testDirectExchange() {
        return new DirectExchange(RabbitMQConstant.TEST_DIRECT_EXCHANGE);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding directBinding(@Qualifier(RabbitMQConstant.TEST_DIRECT_EXCHANGE) DirectExchange exchange,
                                 @Qualifier(RabbitMQConstant.TEST_DIRECT_QUEUE) Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(DIRECT_ROUTING);
    }
}
