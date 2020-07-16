package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @package com.example.demo.controller
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/15 10:02
 **/
//@RestController
@Controller
@RequestMapping("/demo/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public UserEntity test(String userName,String userPassword) {
        System.out.println(userName);
        return userService.getInfo(userName,userPassword);
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
