package com.record.tcgl.service;

import com.record.tcgl.entity.SysUserDetails;

/**
 * 根据用户名查用户信息
 *
 * @author Shuguang_Liux
 * @date 2021/06/19 01:59
 */

public interface LoadUserService {
    SysUserDetails loadUserByUsername(String username);
}
