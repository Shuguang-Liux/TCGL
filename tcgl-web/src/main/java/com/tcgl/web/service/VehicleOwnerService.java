package com.tcgl.web.service;

import com.alibaba.fastjson.JSONObject;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;

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
    ResultVo<?> insertVehicleOwnerAndPayment(JSONObject param);

    /**
     * 出口车主
     * VehicleOwer导出
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link ResultVo}<{@link Map}<{@link String}, {@link Object}>>
     */
    ResultVo<Map<String,Object>> exportVehicleOwner(VehicleOwnerEntity vehicleOwnerEntity);

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
    Map<String,String[]> exportVehicleOwnerAndHistory(VehicleOwnerEntity vehicleOwnerEntity,Integer headTitlesLength);
}
