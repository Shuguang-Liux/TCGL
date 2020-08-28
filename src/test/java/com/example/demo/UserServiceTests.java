package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTests {


    @Autowired
    UserService userService;
    @Test
    public void test(){
        User user = new User();
        user.setUserName(null);
        user.setUserPassword("123456");
        Result result = userService.getInfo(user);
        System.out.println(result.getMessage());
    }

    /**
     * 用户存在测试
     */
    @Test
    public void testUserIsExist(){
        boolean a = userService.countUser("admin",0);
        System.out.println(a);
    }

    /**
     * 用户信息插入测试
     */
    @Test
    @Rollback
    public void testInsertUserInfo(){
        User user = new User();
        user.setUserName("liu");
        user.setUserPassword("123456");
        Result result = userService.insertUserInfo(user);
        System.out.println(result.getMessage());
    }
}
