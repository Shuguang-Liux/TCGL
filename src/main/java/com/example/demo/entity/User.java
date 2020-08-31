package com.example.demo.entity;


import lombok.Data;
import java.io.Serializable;

/**
 * @author Shuguang_Liux
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = -5433003923601251092L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户角色
     */
    private Integer userRole;

    /**
     * 删除状态
     */
    private String deleteState;

}