package com.demo.rabbit.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2021/4/8 17:15
 */
@Service
@RabbitListener(queues = {"sms.direct.queue"})
public class DirectSMSConsumer {


    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms direct---接收到的订单信息是:=>"+message);
    }
}
