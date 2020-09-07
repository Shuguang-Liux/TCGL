package com.example.demo.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.entity.UserEntity;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
//    @Reference(version = "1.0.0",timeout = 300)
    private UserService userService;

    @CrossOrigin
    /**
     * 管理员用户登录
     */
    @RequestMapping(value = "/login")
    public Result getUserInfo(@RequestBody UserEntity requestUserEntity){
        Result result = userService.getInfo(requestUserEntity);
        return result;
    }

    /**
     * 根据用户名称更新用户信息
     * @param requestUserEntity
     */
    @RequestMapping(value = "/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserEntity requestUserEntity){
        Result result = userService.updateUserInfo(requestUserEntity);
        return result;
    }

    /**
     * 用户标记删除
     * @param requestUserEntity
     * @return
     */
    @RequestMapping(value = "/deleteUser")
    public Result deleteUserInfo(@RequestBody UserEntity requestUserEntity){
        Result result = userService.deleteUserInfo(requestUserEntity);
        return result;
    }

    /**
     * 管理员添加用户信息（默认为普通用户）
     * @param requestUserEntity
     * @return
     */
    @RequestMapping(value = "/insertUseInfo")
    public Result insertUseInfo(@RequestBody UserEntity requestUserEntity){
        Result result = userService.insertUserInfo(requestUserEntity);
        return result;
    }

}

