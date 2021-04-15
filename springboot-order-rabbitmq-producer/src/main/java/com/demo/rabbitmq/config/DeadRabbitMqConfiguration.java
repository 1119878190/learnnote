package com.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lx
 * @date 2021/4/8 16:54
 *
 * rabbitmq 死性队列  基于TTl过期时间队列
 *
 * 只是将过期时间队列里的数据又交换机存入死性队列中，要在TTL队列配置"x-dead-letter-exchange"
 */
@Configuration
public class DeadRabbitMqConfiguration {


//1.声明注册direct模式的交换机
    @Bean
    public DirectExchange directDirectExchange(){
        return new DirectExchange("dead_direct_exchange",true,false);
    }

//2.声明队列a
    @Bean
    public Queue deadQueue(){
        return new Queue("dead.direct.queue",true);
    }


//3.完成绑定关系(队列和交换机绑定)
    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind(deadQueue()).to(directDirectExchange()).with("dead");
    }

}
