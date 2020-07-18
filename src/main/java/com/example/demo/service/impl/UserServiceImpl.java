package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

/**
 * @package com.example.demo.service.impl
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/16 9:20
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity getInfo(UserEntity userEntity){
        if (userEntity != null){
            return userMapper.getInfo(userEntity);
        }
        return null;

    }
}
