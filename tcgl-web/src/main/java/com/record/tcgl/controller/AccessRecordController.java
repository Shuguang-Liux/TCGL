package com.record.tcgl.controller;

import com.record.tcgl.service.AccessRecordService;
import com.record.tcgl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 访问记录控制器
 *
 * @author Shuguang_Liux
 * @date 2021/06/23 16:56
 */
@RestController
@RequestMapping("accessRecord")
public class AccessRecordController {

    @Autowired
    private AccessRecordService accessRecordService;


    /**
     * 保存记录的信息
     *
     * @param licensePlate 车牌
     * @return {@link ResultVo<Map<String, Object>>}
     */
    @RequestMapping("/Ocr")
    @PreAuthorize(value = "hasRole('ADMIN') or hasPermission('user/role','sys:role:info')")
    public ResultVo<Map<String,Object>> saveRecordByInfo(String licensePlate){
        return accessRecordService.saveRecordByInfo(licensePlate);
    }
}
