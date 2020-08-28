package com.example.demo.controller;



import com.example.demo.entity.User;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    /**
     * 管理员用户登录
     */
    @RequestMapping(value = "/login")
    public Result getUserInfo(@RequestBody User requestUser){
        Result result = userService.getInfo(requestUser);
        return result;
    }

    /**
     * 根据用户名称更新用户信息
     * @param requestUser
     */
    @RequestMapping(value = "/updateUserInfo")
    public Result updateUserInfo(@RequestBody User requestUser){
        Result result = userService.updateUserInfo(requestUser);
        return result;
    }

    /**
     * 用户标记删除
     * @param requestUser
     * @return
     */
    @RequestMapping(value = "/deleteUser")
    public Result deleteUserInfo(@RequestBody User requestUser){
        Result result = userService.deleteUserInfo(requestUser);
        return result;
    }

    /**
     * 管理员添加用户信息（默认为普通用户）
     * @param requestUser
     * @return
     */
    @RequestMapping(value = "/insertUseInfo")
    public Result insertUseInfo(@RequestBody User requestUser){
        Result result = userService.insertUserInfo(requestUser);
        return result;
    }

}

