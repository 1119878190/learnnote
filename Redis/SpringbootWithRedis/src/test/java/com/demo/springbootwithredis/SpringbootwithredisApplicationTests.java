package com.demo.springbootwithredis;

import com.alibaba.fastjson.JSON;
import com.demo.springbootwithredis.utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.Diff;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootwithredisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {

        //redisTemplate.opsForZSet().add("salary","zhangsan",1000);
        //redisTemplate.opsForZSet().add("salary","lisi",2000);
        //redisTemplate.opsForZSet().add("salary","王五",3000);

        //Set<ZSetOperations.TypedTuple<String>> salary = redisTemplate.opsForZSet().reverseRangeWithScores("salary",0,10000);
//        for (Object o : salary) {
//            System.out.println(o);
//        }
        //System.out.println(JSON.toJSONString(salary));
        //System.out.println(salary);

        redisTemplate.opsForValue().set("date",123,10, TimeUnit.SECONDS);
    }

    @Test
    public void test2(){
        redisUtils.set("date",3);
    }

}
