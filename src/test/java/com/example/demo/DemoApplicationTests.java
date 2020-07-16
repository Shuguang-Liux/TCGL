package com.example.demo;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void test(){
        UserEntity userEntity = new UserEntity();
        userEntity = userService.getInfo("admin","admin");
        System.out.println("该用户ID是：");
        System.out.println(userEntity.getId());
    }

}
