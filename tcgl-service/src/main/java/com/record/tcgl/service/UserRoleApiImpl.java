package com.record.tcgl.service;

import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.dao.UserRoleDao;
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
