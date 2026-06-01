package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.config.RabbitMQConfig;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rmq")
public class MessageController {

//    @Autowired
//    RabbitMQConfig rabbitMQConfig;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToRMQ(@RequestBody Map<String,String> msg){
        rabbitTemplate.convertAndSend(msg.get("exchange-name"),msg.get("routing-key"),msg.get("message"));
//        for(int i=0;i<260;i++){
//            rabbitTemplate.convertAndSend("second-exchange","second","message"+i);
//        }
        return ResponseEntity.ok("Send Successful");
    }

    //publisher confirm callback.
//    rabbitTemplate.setConfirmCallback(
//            (correlationData,ack,cause)->
//
//    {
//
//        if (ack) {
//            System.out.println("Message confirmed");
//        } else {
//            System.out.println("Failed: " + cause);
//        }
//    }
//);
}
