package com.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 * @date 2021/4/8 16:54
 * TTL 设置过期时间   1.指定队列的整个过期时间
 *                    2.给指定消息设置过期时间
 */
@Configuration
public class TTLRabbitMqConfiguration {


//1.声明注册fanout模式的交换机
    @Bean
    public FanoutExchange ttlExchange(){
        return new FanoutExchange("ttl_order_exchange",true,false);
    }

    @Bean
    public FanoutExchange ttlMessageExchange(){
        return new FanoutExchange("ttl_message_order_exchange",true,false);
    }

//2.第一种   给整个队列设置过期时间
    @Bean
    public Queue ttlQueue(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",5000);
        return new Queue("ttl.fanout.queue",true,false,false,args);
    }

//2. 给二种  给单个消息设置过期时间
    @Bean
    public Queue ttlMessageQueue(){
        return new Queue("ttl.message.fanout.queue",true);
    }

//3.完成绑定关系(队列和交换机绑定)
    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlExchange());
    }

    @Bean
    public Binding ttlMessageBinding(){
        return BindingBuilder.bind(ttlMessageQueue()).to(ttlMessageExchange());
    }

}
