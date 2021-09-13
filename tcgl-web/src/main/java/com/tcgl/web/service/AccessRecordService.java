package com.tcgl.web.service;

import com.tcgl.common.vo.ResultVo;
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
    ResultVo<?> saveRecordByInfo(String licensePlate,String type);
}
