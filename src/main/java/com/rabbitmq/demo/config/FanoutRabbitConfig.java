package com.rabbitmq.demo.config;

import com.rabbitmq.demo.constant.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FanoutRabbitConfig {

    /**
     * 队列名
     *
     * @return
     */
    @Bean
    @Qualifier(RabbitMQConstant.TEST_FANOUT_QUEUE)
    public Queue testFanoutQueue() {
        return new Queue(RabbitMQConstant.TEST_FANOUT_QUEUE, true);
    }

    /**
     * 广播交换机
     *
     * @return
     */
    @Bean
    @Qualifier(RabbitMQConstant.TEST_FANOUT_EXCHANGE)
    public FanoutExchange testFanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.TEST_FANOUT_EXCHANGE);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding fanoutBinding(@Qualifier(RabbitMQConstant.TEST_FANOUT_EXCHANGE) FanoutExchange exchange,
                                 @Qualifier(RabbitMQConstant.TEST_FANOUT_QUEUE) Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange);
    }
}
