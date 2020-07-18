package com.example.demo;

import com.example.demo.entity.ScoreEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.ScoreEntityMapper;
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
    private UserService userService;
    @Test
    public void test(){
        UserEntity userEntity = new UserEntity();
        userEntity = userService.getInfo("admin","admin");
        System.out.println("该用户ID是：");
        System.out.println(userEntity.getId());
    }

    @Autowired
    private ScoreEntityMapper scoreEntityMapper;
    @Test
    @Rollback(false)
    public void testInsertScore(){
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setScoreNum("99");
        scoreEntity.setScoreSubject("数学");
        scoreEntityMapper.insert(scoreEntity);
        System.out.println("插入成功");
    }
}
