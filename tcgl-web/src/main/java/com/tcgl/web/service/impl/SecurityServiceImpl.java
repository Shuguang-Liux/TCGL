package com.tcgl.web.service.impl;

import com.tcgl.serviceapi.api.AuthApi;
import com.tcgl.serviceapi.api.RoleApi;
import com.tcgl.serviceapi.api.UserApi;
import com.tcgl.serviceapi.entity.Auth;
import com.tcgl.serviceapi.entity.Role;
import com.tcgl.serviceapi.entity.UserEntity;
import com.tcgl.web.security.entity.SysUserDetails;
import com.tcgl.web.service.SecurityService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 安全及权限通用接口实现类
 *
 * @author Shuguang_Liux
 * @date 2021/06/23 15:59
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @DubboReference
    private RoleApi roleApi;

    @DubboReference
    private AuthApi authApi;

    @DubboReference
    private UserApi userApi;

    /**
     * 角色
     *
     * @param userId 用户id
     * @return {@link List < Role >}
     */
    @Override
    public List<Role> roles(Long userId) {
        return roleApi.findRoleByUserId(userId);
    }

    /**
     * 身份验证
     *
     * @param userId 用户id
     * @return {@link List< Auth >}
     */
    @Override
    public List<Auth> auths(Long userId) {
        return authApi.findAuthByUserId(userId);
    }

    /**
     * 加载用户的用户名
     *
     * @param username 用户名
     * @return {@link SysUserDetails}
     */
    @Override
    public SysUserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userApi.getUserInfo(username);
        if (userEntity != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(userEntity, sysUserDetails);
            // 角色集合
            Set<GrantedAuthority> authorities = new HashSet<>();

            List<Role> roleList = roleApi.findRoleByUserId(userEntity.getId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });
            sysUserDetails.setAuthorities(authorities);

            return sysUserDetails;
        }
        return null;
    }
}
