package com.record.tcgl.service;

import com.alibaba.fastjson.JSONObject;
import com.record.tcgl.entity.VehicleOwnerEntity;
import com.record.tcgl.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/16 15:43
 **/
public interface VehicleOwnerService {
    /**
     * 插入用户信息并存入支付信息
     * @param param
     * @return
     */
    ResultVo<String> insertVehicleOwnerAndPayment(JSONObject param);

    /**
     * VehicleOwer导出
     * @param param
     * @return
     */
    ResultVo<Map<String,Object>> exportVehicleOwner(Map<String,Object> param);

    /**
     * 查询车辆所有人和历史记录列表
     * @param param
     * @return
     */
    ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(JSONObject param);

    /**
     * 自测试导出
     * @param param
     * @param headTitlesLength
     * @return
     */
    Map<String,String[]> exportVehicleOwnerAndHistory(Map<String, Object> param,Integer headTitlesLength);
}
