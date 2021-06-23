package com.record.tcgl.service.impl;

import com.record.tcgl.api.RoleApi;
import com.record.tcgl.api.UserApi;
import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.entity.Role;
import com.record.tcgl.entity.UserRole;
import com.record.tcgl.security.entity.SysUserDetails;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.LoadUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
