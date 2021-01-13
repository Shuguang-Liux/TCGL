package com.record.tcgl.service;

import com.record.tcgl.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/15 0:28
 **/
public interface AccessRecordService {
    /**
     * 由第三方识别工具传入数据
     * @param licensePlate
     * @return
     */
    ResultVo<Map<String,Object>> saveRecordByInfo(String licensePlate);
}
