package com.demo.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author lx
 * @date 2021/4/8 16:47
 */
@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 模拟用户下单
     * @param userId
     * @param productId
     * @param num
     */
    public void makeOrder(String userId,String productId,int num){

        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        //3.通过mq
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);

    }
}
