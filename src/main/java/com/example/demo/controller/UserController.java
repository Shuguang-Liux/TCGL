package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "api/sysUser/getSomething")
    @ResponseBody

    public UserEntity getUserInfo(){
        UserEntity userEntity = userService.getInfo("admin","admin");
        return userEntity;
    }
//    public Result login(@RequestBody UserEntity requestUser) {
//        // 对 html 标签进行转义，防止 XSS 攻击
//        String username = requestUser.getUserName();
//        username = HtmlUtils.htmlEscape(username);
//        if (!Objects.equals("admin", username) || !Objects.equals("admin", requestUser.getUserPassword())) {
//            String message = "账号密码错误";
//            System.out.println("test");
//            return new Result(400);
//        } else {
//            return new Result(200);
//        }
//    }
}

