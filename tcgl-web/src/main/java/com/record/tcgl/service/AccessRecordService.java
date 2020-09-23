package com.record.tcgl.service;

import com.record.tcgl.vo.ResultVo;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/15 0:28
 **/
public interface AccessRecordService {
    ResultVo<Map<String,Object>> saveRecordByInfo(String licensePlate);
}
