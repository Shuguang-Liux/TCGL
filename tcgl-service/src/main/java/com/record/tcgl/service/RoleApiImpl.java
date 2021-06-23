package com.record.tcgl.service;

import com.record.tcgl.api.RoleApi;
import com.record.tcgl.dao.RoleDao;
import com.record.tcgl.dao.UserRoleDao;
import com.record.tcgl.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户角色API接口实现类
 *
 * @author Shuguang_Liux
 * @date 2021/06/22 22:01
 */
public class RoleApiImpl implements RoleApi {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return roleDao.findRoleByUserId(userId);
    }
}
