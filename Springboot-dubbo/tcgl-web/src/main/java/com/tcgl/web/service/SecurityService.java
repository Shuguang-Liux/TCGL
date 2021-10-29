package com.tcgl.web.service;

import com.tcgl.serviceapi.entity.Auth;
import com.tcgl.serviceapi.entity.Role;
import com.tcgl.web.security.entity.SysUserDetails;

import java.util.List;

/**
 * 安全及权限通用接口
 *
 * @author Shuguang_Liux
 * @date 2021/06/23 15:57
 */
public interface SecurityService {

    /**
     * 角色
     *
     * @param userId 用户id
     * @return {@link List<Role>}
     */
    List<Role> roles(Long userId);


    /**
     * 身份验证
     *
     * @param userId 用户id
     * @return {@link List<Auth>}
     */
    List<Auth> auths(Long userId);

    /**
     * 加载用户的用户名
     *
     * @param username 用户名
     * @return {@link SysUserDetails}
     */
    SysUserDetails loadUserByUsername(String username);
}
