package com.demo.rabbitmq.all;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lx
 * @date 2021/4/7 15:57
 *
 * 全部由代码完成 包括exchange,queue的创建与绑定  没有在web页面配置
 */
public class Producer  implements Runnable{
    public static void main(String[] args) {

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
        Connection connection = null;
        Channel channel = null;
        try {
            connection  = connectionFactory.newConnection("生产者");
            //3.通过连接获取通道Channel
            channel = connection.createChannel();
            //4.通过创建交换机,什么队列,绑定关系,路由key,发送消息,和接受消息
            //String queueName = "queue1";
            //5.准备消息内容
            String message = "Hello mq routing";
            //6.准备交换机
            String exchangeName = "direct_message_exchage";
            String routeKey = "";
            String type = "direct"; //交换机类型: direct topic fanout headers
            //创建交换机
            channel.exchangeDeclare(exchangeName,type,true);
            /**
             * 声明队列
             * @params1  队列名字
             * @params2  是否要持久化  所谓的持久化消息是否存盘,
             * @params3  排他性 是否是独占独立
             * @params4  是否自动删除,随着最后一个消费者消费完消息后是否把队列中的消息删除
             * @params5 携带附属参数
             */
            channel.queueDeclare("quque5",true,false,false,null);
            channel.queueDeclare("quque6",true,false,false,null);
            channel.queueDeclare("quque7",true,false,false,null);

            //绑定队列
            channel.queueBind("quque5",exchangeName,"order");
            channel.queueBind("quque6",exchangeName,"order");
            channel.queueBind("quque7",exchangeName,"course");
            //6.发送消息给队列queue
            /**
             * @params1 交换机
             * @params2 队列名称/routingkey
             * @params3 消息的状态控制
             * @params4 消息内容
             */
            channel.basicPublish(exchangeName,"course",null,message.getBytes());

            System.out.println("消息发送成功");


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

    public void run() {

    }
}
