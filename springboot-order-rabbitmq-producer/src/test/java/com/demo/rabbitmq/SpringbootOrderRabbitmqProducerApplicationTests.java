package com.demo.rabbitmq;

import com.demo.rabbitmq.service.DirectOrderService;
import com.demo.rabbitmq.service.OrderService;
import com.demo.rabbitmq.service.TTLOrderService;
import com.demo.rabbitmq.service.TopicOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
class SpringbootOrderRabbitmqProducerApplicationTests {


    @Resource
    private OrderService orderService;

    @Autowired
    private DirectOrderService directOrderService;

    @Autowired
    private TopicOrderService topicOrderService;

    @Autowired
    private TTLOrderService ttlOrderService;

    @Test
    void contextLoads() {
        orderService.makeOrder("1","1",12);
        System.out.println("消息发送成功");
    }

    @Test
    void directTest(){
        directOrderService.makeOrder("2","2",12);
        System.out.println("消息发送成功");
    }

    @Test
    void topicTest(){
        topicOrderService.makeOrder("3","3",12);
        System.out.println("消息发送成功");
    }

    @Test
    void TTLTest(){
        ttlOrderService.makeOrder("4","4",12);
        System.out.println("消息发送成功");
    }
    @Test
    void TTLMessageTest(){
        ttlOrderService.ttlMessageOrder("5","5",12);
        System.out.println("消息发送成功");
    }



    @Test
    void testto(){
        int[] arr = {1,2,2,2,3,2,2,2,2};

        Map<Integer,Integer> map = new HashMap<>();
        for (int item : arr) {
            if (map.containsKey(item)){
                int temp = map.get(item);
                map.put(item,temp+1);
            }else {
                map.put(item,1);
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if (value > arr.length/2){
                System.out.println(key+":"+value);
            }

        }


    }

    @Test
    public void testGit(){
        System.out.println("测试git");
    }

}
