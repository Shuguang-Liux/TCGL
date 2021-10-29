package com.tcgl.serviceapi.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;

import java.util.Map;


/**
 * 车主api
 *
 * @author sun
 * @date 2021/06/24
 */
public interface VehicleOwnerApi {

    /**
     * 插入车主
     *
     * @param param 参数
     * @return {@link ResultVo<String>}
     */
    ResultVo<?> insertVehicleOwner(VehicleOwnerEntity vehicleOwnerEntity);


    /**
     * 选择的页面
     *
     * @param param 参数
     * @return {@link ResultVo<IPage<VehicleOwnerEntity>>}
     */
    IPage<VehicleOwnerEntity> selectByPage(VehicleOwnerEntity vehicleOwnerEntity);


    /**
     * 出口车主清单
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String,Object> exportVehicleOwnerList(VehicleOwnerEntity vehicleOwnerEntity);


    /**
     * 让车主和访问历史记录
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link ResultVo<VehicleOwnerEntity>}
     */
    ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(VehicleOwnerEntity vehicleOwnerEntity);


    /**
     * 出口车主和历史
     *
     * @param param           参数
     * @param headTitleLength 头标题长度
     * @return {@link Map<String, String[]>}
     */
    Map<String,String[]> exportVehicleOwnerAndHistory(VehicleOwnerEntity vehicleOwnerEntity,Integer headTitleLength);

}
