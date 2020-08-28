package com.example.demo.controller;

import com.example.demo.entity.Record;
import com.example.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @package com.example.demo.controller
 * @Description ToDo
 * @Editor Shuguang_Liux
 * @Date 2020/8/11 14:04
 **/
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/finglist")
    public List<Record> findList(@RequestBody Record record){
        return recordService.selectInfoByPage(record);
    }
}
