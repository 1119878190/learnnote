package com.demo.rabbit.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2021/4/8 17:14
 */
@Service
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "duanxin.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "#.duanxin.#"
))

public class TopicDuanxinConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("duanxin topic---接收到的订单信息是:=>"+message);
    }
}
