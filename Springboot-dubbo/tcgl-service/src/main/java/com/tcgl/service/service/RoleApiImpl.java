package com.tcgl.service.service;

import com.tcgl.service.dao.RoleDao;
import com.tcgl.serviceapi.api.RoleApi;
import com.tcgl.serviceapi.entity.Role;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户角色API接口实现类
 *
 * @author Shuguang_Liux
 * @date 2021/06/22 22:01
 */
@DubboService
public class RoleApiImpl implements RoleApi {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return roleDao.findRoleByUserId(userId);
    }
}
