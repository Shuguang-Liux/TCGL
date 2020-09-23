package com.record.tcgl.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.record.tcgl.entity.VehicleOwnerEntity;
import com.record.tcgl.vo.ResultVo;

import java.util.Map;
import java.util.Set;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020/9/15 0:06
 **/
public interface VehicleOwnerApi {
    /**
     * 插入用户信息同时插入租金信息表
     * @param param
     * @return
     */
    ResultVo<String> insertVehicleOwner(JSONObject param);

    /**
     * 列表查询
     * @param param
     * @return
     */
    ResultVo<IPage<VehicleOwnerEntity>> selectByPage(Map<String,String> param);

    /**
     * 导出
     * @param param
     * @return
     */
    ResultVo<Map<String,Object>> exportVehicleOwnerList(Map<String,Object> param);

    /**
     * 获取VehicleOwner和AccessRecordHistory对应列表信息
     * @param vehicleOwnerEntity
     * @return
     */
    ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(VehicleOwnerEntity vehicleOwnerEntity);

}
