package com.record.tcgl.service;

import com.record.tcgl.api.AuthApi;
import com.record.tcgl.dao.AuthDao;
import com.record.tcgl.entity.Auth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户权限接口实现类
 *
 * @author Shuguang_Liux
 * @date 2021 /06/22 16:33
 */
public class AuthApiImpl implements AuthApi {

    @Autowired
    private AuthDao authDao;

    /**
     * 根据用户ID查询用户权限
     *
     * @param userId 用户ID
     * @return List<UserAuth>
     * @author Shuguang_Liux
     * @date 2021/6/22 16:34
     */

    @Override
    public List<Auth> findAuthByUserId(Long userId) {
        return authDao.findAuthByUserId(userId);
    }
}
