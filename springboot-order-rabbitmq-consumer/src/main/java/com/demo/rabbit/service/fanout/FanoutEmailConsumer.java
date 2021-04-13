package com.demo.rabbit.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2021/4/8 17:14
 */
@Service
@RabbitListener(queues = {"email.fanout.queue"})
public class FanoutEmailConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("email fanout---接收到的订单信息是:=>"+message);
    }
}
