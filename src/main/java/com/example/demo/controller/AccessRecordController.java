package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.AccessRecordEntity;
import com.example.demo.result.Result;
import com.example.demo.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @package com.example.demo.controller
 * @Description ToDo
 * @Editor Shuguang_Liux
 * @Date 2020/8/11 14:04
 **/
@RestController
@RequestMapping("/accessRecord")
public class AccessRecordController {

    @Autowired
    AccessRecordService accessRecordService;

    /**
     * 列表展示
     * @param params
     * @return
     */
    @RequestMapping(value = "/finglist")
    public IPage<AccessRecordEntity> findList(@RequestBody Map<String,Object> params){
        return accessRecordService.selectInfoByPage(params);
    }

    @RequestMapping(value = "/InfoByFront")
    public Result saveInfoByFront (String licensePlate){
        return accessRecordService.saveRecordByFront(licensePlate);
    }
}
