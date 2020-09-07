package com.example.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.AccessRecordEntity;
import com.example.demo.service.AccessRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.example.demo
 * @Description ToDo
 * @Date 2020/8/27 23:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessRecordEntityEntityServiceTest {

    @Autowired
    AccessRecordService accessRecordService;

    @Test
    public void testGetRecordInfoByPage(){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("sourceId",1);
        IPage<AccessRecordEntity> iPageList = accessRecordService.selectInfoByPage(objectMap);
        System.out.println(iPageList);
    }

    @Test
    @Rollback(false)
    public void testUpdateByFront(){
        accessRecordService.saveRecordByFront("鲁K12345");
    }
}
