package com.example.demo;

import com.example.demo.entity.Record;
import com.example.demo.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Shuguang_Liux
 * @package com.example.demo
 * @Description ToDo
 * @Date 2020/8/27 23:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

    @Autowired
    RecordService recordService;

    @Test
    public void testGetRecordInfoByPage(){
        Record record = new Record();
//        record.setOwnerName("贾祥龙");
        List<Record> list = recordService.selectInfoByPage(record);
        for (Record record1 : list) {
            System.out.println(record1.getLicensePlate());
        }
    }
}
