package com.tcgl.web.service.impl;

import com.tcgl.serviceapi.api.RoleApi;
import com.tcgl.serviceapi.api.UserApi;
import com.tcgl.web.service.LoadUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * 查询用户信息
 *
 * @author Shuguang_Liux
 * @date 2021/06/19 02:13
 */
@Service
public class LoadUserServiceImpl implements LoadUserService {


    @DubboReference
    private UserApi userApi;

    @DubboReference
    private RoleApi roleApi;


}
