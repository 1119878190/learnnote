package com.tzinfo.mybatisplus;

import com.github.pagehelper.PageInfo;
import com.tzinfo.mybatisplus.controller.service.UserService;
import com.tzinfo.mybatisplus.dao.UserMapper;
import com.tzinfo.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisplusApplicationTests {


    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    void testInsert(){
        User user = new User();
        user.setName("测试");
        user.setEmail("11111@qq.com");
        user.setAge(33);
        userMapper.insert(user);
    }

    @Test
    void testLogicDelete(){
        int i = userMapper.deleteById(6);
        System.out.println(i);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setId(1384035709071007745L);
        user.setName("testUpdate");
        userMapper.updateById(user);
    }


    @Test
    void testPageHelperWithMybatisPlus(){
        PageInfo<User> pageInfo = userService.listUsersByPage(1, 3);
        System.out.println("PageSize:"+pageInfo.getPageSize());
        System.out.println(pageInfo.getList());

    }

}
