package com.tzinfo.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuxu
 * date 2021-04-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.ASSIGN_ID )//主键 使用雪花算法
    Long id;

    String name;

    Integer age;

    String email;

    @TableLogic//逻辑删除
    int deleted;

    @TableField(fill = FieldFill.INSERT)
    Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    Date updateTime;

}
