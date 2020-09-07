package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
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
class UserEntityEntityServiceTests {


    @Autowired
    UserService userService;
    @Test
    public void test(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(null);
        userEntity.setUserPassword("123456");
        Result result = userService.getInfo(userEntity);
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
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("liu");
        userEntity.setUserPassword("123456");
        Result result = userService.insertUserInfo(userEntity);
        System.out.println(result.getMessage());
    }


    @Autowired
    private UserDao userDao;
    @Test
    @Rollback(false)
    public void testInsertUserInfoNew(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("测试");
        userEntity.setUserPassword("123456");
        userEntity.setUserRole(1);
        userEntity.setDeleteState("N");
        userDao.insert(userEntity);

    }
}