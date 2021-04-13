package com.demo.rabbitmq.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author lx
 * @date 2021/4/8 16:47
 */
@Service
public class TTLOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 给整个队列设置过期时间
     */
    public void makeOrder(String userId,String productId,int num){
        String orderId = UUID.randomUUID().toString();

        String exchangeName = "ttl_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName,"",orderId);


    }
    /**
     * 给单个消息设置过期时间
     */
    public void ttlMessageOrder(String userId,String productId,int num){
        String orderId = UUID.randomUUID().toString();
        String exchangeName = "ttl_message_order_exchange";
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("utf-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangeName,"",orderId,messagePostProcessor);

    }

}
