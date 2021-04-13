package com.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lx
 * @date 2021/4/8 16:54
 */
@Configuration
public class DirectRabbitMqConfiguration {


//1.声明注册direct模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }

//2.声明队列 sms.direct.queue  email.direct.queue  duanxin.direct.queue
    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue",true);
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("email.direct.queue",true);
    }
    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue("duanxin.direct.queue",true);
    }

//3.完成绑定关系(队列和交换机绑定)
    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }
    @Bean
    public Binding duanxinDirectBinding(){
        return BindingBuilder.bind(duanxinDirectQueue()).to(directExchange()).with("duanxin");
    }
}
