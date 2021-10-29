package com.tcgl.serviceapi.api;

import com.tcgl.serviceapi.entity.Role;

import java.util.List;

/**
 * 用户角色API接口
 *
 * @author Shuguang_Liux
 * @date 2021/06/22 22:00
 */
public interface RoleApi {

    /**
     * 通过用户id找到角色
     *
     * @param userId 用户id
     * @return {@link List< Role >}
     */
    List<Role> findRoleByUserId(Long userId);
}
