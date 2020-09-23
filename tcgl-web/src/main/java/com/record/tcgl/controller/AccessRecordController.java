package com.record.tcgl.controller;

import com.record.tcgl.service.AccessRecordService;
import com.record.tcgl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AccessRecordService accessRecordService;
    @RequestMapping("/Ocr")
    public ResultVo<Map<String,Object>> saveRecordByInfo(String licensePlate){
        return accessRecordService.saveRecordByInfo(licensePlate);
    }
}
