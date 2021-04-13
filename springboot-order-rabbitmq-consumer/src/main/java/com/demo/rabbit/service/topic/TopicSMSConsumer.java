package com.demo.rabbit.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2021/4/8 17:15
 */
@Service
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "com.#"
))
public class TopicSMSConsumer {


    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms topic---接收到的订单信息是:=>"+message);
    }
}
