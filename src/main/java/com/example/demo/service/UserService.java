package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.result.Result;

/**
 * @package com.example.demo.service
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/15 17:31
 **/
public interface UserService {

    Result getInfo(User user);

    Result updateUserInfo(User user);

    Result deleteUserInfo(User user);

    Result insertUserInfo(User user);

    /**
     * 查询用户是否存在
     * @param userName
     * @param userRole
     * @return
     */
    boolean countUser(String userName, int userRole);
}
