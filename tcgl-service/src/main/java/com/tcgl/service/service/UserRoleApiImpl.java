package com.tcgl.service.service;

import com.tcgl.service.dao.UserRoleDao;
import com.tcgl.serviceapi.api.UserRoleApi;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户角色实现类
 *
 * @author Shuguang_Liux
 * @date 2021/06/22 18:01
 */
public class UserRoleApiImpl implements UserRoleApi {

    @Autowired
    private UserRoleDao userRoleDao;

}
