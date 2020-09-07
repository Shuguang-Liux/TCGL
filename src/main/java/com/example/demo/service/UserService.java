package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.result.Result;

/**
 * @package com.example.demo.service
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/15 17:31
 **/
public interface UserService {

    Result<String> getInfo(UserEntity userEntity);

    Result updateUserInfo(UserEntity userEntity);

    Result deleteUserInfo(UserEntity userEntity);

    Result insertUserInfo(UserEntity userEntity);

    /**
     * 查询用户是否存在
     * @param userName
     * @param userRole
     * @return
     */
    boolean countUser(String userName, int userRole);
}
