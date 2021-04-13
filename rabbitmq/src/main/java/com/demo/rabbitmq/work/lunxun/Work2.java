package com.demo.rabbitmq.work.lunxun;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lx
 * @date 2021/4/7 15:57
 */
public class Work2 implements Runnable {
    public static void main(String[] args) {

        new Thread(new Work2(),"queue1").start();


    }

    public void run() {
        //所有的中间件技术都欧式给予tcp/ip协议基础之上构建新型的协议规范,只不过rabbitmq遵循amqp
        //ip port

        //1.创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.0.107");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        //2.创建连接Connection
        final String queueName = Thread.currentThread().getName();
        Connection connection = null;
        Channel channel = null;
        try {
            connection  = connectionFactory.newConnection("生产者");
            //3.通过连接获取通道Channel
            channel = connection.createChannel();
            //4.通过创建交换机,什么队列,绑定关系,路由key,发送消息,和接受消息

            Channel finalChannel = channel;
            finalChannel.basicConsume(queueName, true, new DeliverCallback() {
                public void handle(String s, Delivery message) throws IOException {
                    System.out.println(queueName+"收到的消息是:"+new String(message.getBody(),"UTF-8"));
                }
            },new CancelCallback(){
                public void handle(String s) throws IOException {
                    System.out.println("失败了");
                }
            });

            System.out.println("开始接受消息");
            System.in.read();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            //7关闭通道
            if (channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            //8.关闭连接
            if (connection != null && connection.isOpen()){

                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
