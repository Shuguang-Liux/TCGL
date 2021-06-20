package com.record.tcgl.api;

import com.record.tcgl.entity.SysUserDetails;
import com.record.tcgl.entity.UserEntity;

/**
 * 根据用户名查用户信息
 *
 * @author Shuguang_Liux
 * @date 2021/06/19 01:59
 */

public interface LoadUserApi {
    UserEntity loadUserByUsername(String username);
}