package com.tcgl.serviceapi.api;

import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020/9/14 21:29
 **/
public interface PaymentRecordApi {
    /**
     * 插入付款信息
     * 根据关联主键将信息插入租金支付记录表
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link ResultVo}<{@link ?}>
     */
    int insertPaymentInfo(VehicleOwnerEntity vehicleOwnerEntity);
}
