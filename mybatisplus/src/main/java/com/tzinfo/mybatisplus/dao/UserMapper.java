package com.tzinfo.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tzinfo.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuxu
 * date 2021-04-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
