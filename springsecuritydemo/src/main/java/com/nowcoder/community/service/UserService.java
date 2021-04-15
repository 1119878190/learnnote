package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//可以选择其它的service实现UserDetailsService,
//因为该service中有根据用户名查询用户的方法,可以直接调用,故在该类实现
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByName(String username) {
        return userMapper.selectByName(username);
    }

    //根据用户名查询用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByName(username);
    }
}
