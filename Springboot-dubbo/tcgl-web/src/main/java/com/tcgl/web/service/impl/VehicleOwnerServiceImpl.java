package com.tcgl.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.api.VehicleOwnerApi;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;
import com.tcgl.web.service.VehicleOwnerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

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
    public ResultVo<?>  insertVehicleOwnerAndPayment(JSONObject param) {
        return vehicleOwnerApi.insertVehicleOwner(param);
    }

    /**
     * VehicleOwer导出
     * @param param
     * @return
     */
    @Override
    public ResultVo<Map<String, Object>> exportVehicleOwner(VehicleOwnerEntity vehicleOwnerEntity) {
        return ResultVo.ok(vehicleOwnerApi.exportVehicleOwnerList(vehicleOwnerEntity));
    }

    /**
     * 查询车辆所有人和历史记录列表
     * @param param
     * @return
     */
    @Override
    public ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(JSONObject param) {

        VehicleOwnerEntity vehicleOwnerEntity = new VehicleOwnerEntity();
        if (!StringUtils.isEmpty((CharSequence) param.get("licensePlate"))){
            vehicleOwnerEntity.setLicensePlate(String.valueOf(param.get("licensePlate")));
        }
        if (!StringUtils.isEmpty((CharSequence) param.get("vehicleOwner"))){
            vehicleOwnerEntity.setVehicleOwner(String.valueOf(param.get("vehicleOwner")));
        }
        if (!StringUtils.isEmpty((CharSequence) param.get("isValid"))){
            vehicleOwnerEntity.setIsValid(String.valueOf(param.get("isValid")));
        }
        return vehicleOwnerApi.getVehicleOwnerAndAccessRecordHistory(vehicleOwnerEntity);
    }

    /**
     * 自测试导出
     * @param param
     * @param headTitleLength
     * @return
     */
    @Override
    public Map<String, String[]> exportVehicleOwnerAndHistory(VehicleOwnerEntity vehicleOwnerEntity,Integer headTitleLength) {
        return vehicleOwnerApi.exportVehicleOwnerAndHistory(vehicleOwnerEntity,headTitleLength);
    }


}
