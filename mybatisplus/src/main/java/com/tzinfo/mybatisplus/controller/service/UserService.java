package com.tzinfo.mybatisplus.controller.service;

import com.github.pagehelper.PageInfo;
import com.tzinfo.mybatisplus.entity.User;

/**
 * @author liuxu
 * date 2021-04-19
 */
public interface UserService {

    PageInfo<User> listUsersByPage(int currentPage, int size);
}
