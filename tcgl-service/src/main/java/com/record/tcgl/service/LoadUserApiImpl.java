package com.record.tcgl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.record.tcgl.api.LoadUserApi;
import com.record.tcgl.dao.UserDao;
import com.record.tcgl.entity.SysUserDetails;
import com.record.tcgl.entity.UserEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 根据用户名查用户信息
 *
 * @author Shuguang_Liux
 * @date 2021/06/19 02:01
 */
@DubboService
@Component
public class LoadUserApiImpl implements LoadUserApi {

    @Autowired
    private UserDao userDao;
    @Override
    public UserEntity loadUserByUsername(String username) {
        return userDao.selectOne(new QueryWrapper<UserEntity>().eq("username",username));
    }
}
