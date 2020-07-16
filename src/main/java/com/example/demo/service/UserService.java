package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @package com.example.demo.service
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/15 17:31
 **/
public interface UserService {
    UserEntity getInfo(String userName, String userPassword);
}
