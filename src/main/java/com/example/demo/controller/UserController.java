package com.example.demo.controller;



import com.example.demo.entity.UserEntity;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "api/sysUser/getSomething")
//    @ResponseBody
    public Result getUserInfo(@RequestBody UserEntity requestUser){
//        String userName = requestUser.getUserName();
//        String userPassword = requestUser.getUserPassword();
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserName();
        UserEntity userEntity = userService.getInfo(requestUser);
        if (null != userEntity){
            System.out.println("存在此用户，正常登录操作");
            return new Result(200);
        }else {
            return new Result(400);
        }
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

