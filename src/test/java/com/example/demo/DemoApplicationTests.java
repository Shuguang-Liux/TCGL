package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {


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


//    @Test
//    @Rollback(false)
//    public void testUpdateUserInfo(){
//        User user = new User();
//        user.setUserName("admin");
//        user.setUserPassword("admin");
//        int count = userService.updateUserInfo(user);
//        if (count == 1){
//            System.out.println("更新成功");
//        }
//    }

//    @Test
//    public void testDeleteUserInfo(){
//        User user = new User();
//        user.setUserName("1");
//        String retLog = userService.deleteUserInfo(user);
//        System.out.println(retLog);
//    }
}
