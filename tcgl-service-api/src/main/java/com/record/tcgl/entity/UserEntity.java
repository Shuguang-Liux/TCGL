package com.record.tcgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @package com.example.demo.entity
 * @Description 用户登录权限表
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/

@Data
@TableName(value = "t_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1020687110827214873L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 管理员0，普通用户1
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     * 删除状态
     */
    @TableField(value = "delete_state")
    private String deleteState;


    public static final String COL_ID = "id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_PASSWORD = "user_password";

    public static final String COL_USER_ROLE = "user_role";

    public static final String COL_DELETE_STATE = "delete_state";
}