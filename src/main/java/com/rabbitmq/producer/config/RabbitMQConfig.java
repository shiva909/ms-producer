package com.rabbitmq.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME="new-second-queue";
    public static final String EXCHANGE_NAME="new-second-exchange";
    public static final String ROUTING_KEY="new-second";

    @Bean
    public Queue myQueue(){
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    public TopicExchange myExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding myBinding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }


}
