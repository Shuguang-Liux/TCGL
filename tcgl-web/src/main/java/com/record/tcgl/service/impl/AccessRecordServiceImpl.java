package com.record.tcgl.service.impl;

import com.record.tcgl.api.AccessRecordApi;
import com.record.tcgl.service.AccessRecordService;
import com.record.tcgl.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service.impl
 * @Description ToDo
 * @Date 2020/9/15 0:36
 **/
@Service("accessRecordService")
public class AccessRecordServiceImpl implements AccessRecordService {

    @DubboReference
    private AccessRecordApi accessRecordApi;

    @Override
    public ResultVo<Map<String, Object>> saveRecordByInfo(String licensePlate) {
        ResultVo<Map<String,Object>> resultVo = new ResultVo<>();
        if (StringUtils.isEmpty(licensePlate)){
            resultVo.setError(400,"传入信息为空！");
            return resultVo;
        }
        return accessRecordApi.saveRecordByInfo(licensePlate);
    }
}
