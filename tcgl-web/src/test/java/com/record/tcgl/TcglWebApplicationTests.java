package com.record.tcgl;

import com.record.tcgl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;


@SpringBootTest
@ImportResource(locations={"classpath:consumer.xml"})
class TcglWebApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void testAdminUser(){
//        userService.adminRoles("admin",0);
    }

}
