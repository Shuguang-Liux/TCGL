package com.tcgl.web.service.impl;

import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.api.AccessRecordApi;
import com.tcgl.web.service.AccessRecordService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

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
    public ResultVo<?> saveRecordByInfo(String licensePlate,String type) {
        if (StringUtils.isEmpty(licensePlate)){
            return ResultVo.fail(400,"传入信息为空！");
        }
        return accessRecordApi.saveRecordByInfo(licensePlate,type);
    }
}
