package com.record.tcgl.service.impl;

import com.record.tcgl.api.LoadUserApi;
import com.record.tcgl.entity.SysUserDetails;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.LoadUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private LoadUserApi loadUserApi;
    @Override
    public SysUserDetails loadUserByUsername(String username) {
        UserEntity userEntity = loadUserApi.loadUserByUsername(username);
        if (userEntity != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(userEntity, sysUserDetails);

            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合

//            List<UserEntity> roleList = userRoleApi.getUserInfo(username);
//            roleList.forEach(role -> {
//
//            });
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getUsername()));
            sysUserDetails.setAuthorities(authorities);

            return sysUserDetails;
        }
        return null;
    }
}
