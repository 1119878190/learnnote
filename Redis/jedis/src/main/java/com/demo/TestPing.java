package com.demo;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author lx
 * @date 2021/2/16 16:09
 */
public class TestPing {
    public static void main(String[] args) {
        //1.创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        System.out.println(jedis.ping());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "kuangshen");
        String result = jsonObject.toJSONString();

        Transaction multi = jedis.multi();//开启事务


        try {
            multi.set("user1", result);
            multi.set("user2", result);

            multi.exec();
        } catch (Exception e) {
            multi.discard(); //如果出现错误,关闭事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));

        }


    }
}
