package com.record.tcgl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.record.tcgl.api.VehicleOwnerApi;
import com.record.tcgl.entity.VehicleOwnerEntity;
import com.record.tcgl.service.VehicleOwnerService;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service.impl
 * @Description ToDo
 * @Date 2020/9/16 15:43
 **/
@Service("vehicleOwnerService")
public class VehicleOwnerServiceImpl implements VehicleOwnerService {

    @DubboReference
    private VehicleOwnerApi vehicleOwnerApi;
    /**
     * 插入用户信息并存入支付信息
     * @param param
     * @return
     */
    @Override
    public ResultVo<String> insertVehicleOwnerAndPayment(JSONObject param) {
        return vehicleOwnerApi.insertVehicleOwner(param);
    }

    /**
     * VehicleOwer导出
     * @param param
     * @return
     */
    @Override
    public ResultVo<Map<String, Object>> exportVehicleOwner(Map<String, Object> param) {
        ResultVo<Map<String,Object>> resultVo = new ResultVo<>();
        try {
            resultVo = vehicleOwnerApi.exportVehicleOwnerList(param);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo.setError(400,"导出失败，请联系系统管理员");
        }
        return resultVo;
    }

    /**
     * 查询车辆所有人和历史记录列表
     * @param param
     * @return
     */
    @Override
    public ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(JSONObject param) {

        VehicleOwnerEntity vehicleOwnerEntity = new VehicleOwnerEntity();
        if (!StringUtils.isEmpty(param.get("licensePlate"))){
            vehicleOwnerEntity.setLicensePlate(String.valueOf(param.get("licensePlate")));
        }
        if (!StringUtils.isEmpty(param.get("vehicleOwner"))){
            vehicleOwnerEntity.setVehicleOwner(String.valueOf(param.get("vehicleOwner")));
        }
        if (!StringUtils.isEmpty(param.get("isValid"))){
            vehicleOwnerEntity.setIsValid(String.valueOf(param.get("isValid")));
        }
        ResultVo<VehicleOwnerEntity> resultVo = vehicleOwnerApi.getVehicleOwnerAndAccessRecordHistory(vehicleOwnerEntity);
        return resultVo;
    }

    /**
     * 自测试导出
     * @param param
     * @param headTitleLength
     * @return
     */
    @Override
    public Map<String, String[]> exportVehicleOwnerAndHistory(Map<String, Object> param,Integer headTitleLength) {
        return vehicleOwnerApi.exportVehicleOwnerAndHistory(param,headTitleLength);
    }


}
