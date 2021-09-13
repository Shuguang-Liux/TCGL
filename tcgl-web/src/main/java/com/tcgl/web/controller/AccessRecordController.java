package com.tcgl.web.controller;

import com.tcgl.common.vo.ResultVo;
import com.tcgl.web.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResultVo<?> saveRecordByInfo(String licensePlate, String type){
        return accessRecordService.saveRecordByInfo(licensePlate,type);
    }
}
