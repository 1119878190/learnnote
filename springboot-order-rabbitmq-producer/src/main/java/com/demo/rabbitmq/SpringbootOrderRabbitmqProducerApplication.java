package com.demo.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.导入依赖
 * 2.配置连接信息
 * 3.创建配置类  讲交换机与队列绑定
 */
@SpringBootApplication
public class SpringbootOrderRabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOrderRabbitmqProducerApplication.class, args);
    }

}
