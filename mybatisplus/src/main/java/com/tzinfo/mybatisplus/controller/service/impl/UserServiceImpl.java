package com.tzinfo.mybatisplus.controller.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tzinfo.mybatisplus.controller.service.UserService;
import com.tzinfo.mybatisplus.dao.UserMapper;
import com.tzinfo.mybatisplus.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuxu
 * date 2021-04-19
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<User> listUsersByPage(int currentPage, int size) {
        PageHelper.startPage(currentPage, size);
        List<User> users = userMapper.selectList(null);
        PageInfo<User> pageInfo = new PageInfo<>(users);

        System.out.println(pageInfo);
        return pageInfo;
    }
}
