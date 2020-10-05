package com.record.tcgl.api;

import com.record.tcgl.entity.AccessRecordEntity;
import com.record.tcgl.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020/9/14 21:36
 **/
public interface AccessRecordApi {
    /**
     * 保存传入信息
     * @param licensePlate
     * @return
     */
    ResultVo<Map<String,Object>> saveRecordByInfo(String licensePlate);

    /**
     * 根据licensePlate查询列表
     * @param licensePlateSet
     * @return
     */
    ResultVo<Map<String, AccessRecordEntity>> getAccessRecordByLicensePlateSet(Set<String> licensePlateSet);
}
