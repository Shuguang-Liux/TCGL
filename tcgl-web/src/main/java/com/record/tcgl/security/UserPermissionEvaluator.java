package com.record.tcgl.security;

import com.record.tcgl.entity.Auth;
import com.record.tcgl.security.entity.SysUserDetails;
import com.record.tcgl.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 用户权限注解处理类
 *
 * @author Shuguang_Liux
 * @date 2021/06/23
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {


    @Autowired
    private SecurityService securityService;

    /**
     * 判断是否拥有权限
     *
     * @param authentication 用户身份
     * @param targetUrl      目标路径
     * @param permission     路径权限
     * @return 是否拥有权限
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
		// 用户权限
        Set<String> permissions = new HashSet<String>();

        List<Auth> authList = securityService.auths(sysUserDetails.getId());
        authList.forEach(auth -> {
            permissions.add(auth.getPermission());
        });
		// 判断是否拥有权限
		return permissions.contains(permission.toString());
	}

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }

}
