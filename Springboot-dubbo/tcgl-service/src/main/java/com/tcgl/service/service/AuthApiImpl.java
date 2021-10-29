package com.tcgl.service.service;

import com.tcgl.service.dao.AuthDao;
import com.tcgl.serviceapi.api.AuthApi;
import com.tcgl.serviceapi.entity.Auth;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户权限接口实现类
 *
 * @author Shuguang_Liux
 * @date 2021 /06/22 16:33
 */
@DubboService
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
