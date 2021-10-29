package com.tcgl.serviceapi.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @package com.example.demo.entity
 * @Description 用户登录权限表
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/

@Data
@TableName(value = "t_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 571620662071575840L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;


    /**
     * 删除状态
     */
    @TableField(value = "delete_status")
    private int deleteStatus;
}