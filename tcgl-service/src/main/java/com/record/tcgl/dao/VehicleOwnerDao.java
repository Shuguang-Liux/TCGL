package com.record.tcgl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.record.tcgl.entity.VehicleOwnerEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @package com.example.demo.dao
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/
@Mapper
@Repository("vehicleOwnerDao")
public interface VehicleOwnerDao extends BaseMapper<VehicleOwnerEntity> {
    /**
     * 查询用户与入园记录
     * @param vehicleOwnerEntity
     * @return
     */
    VehicleOwnerEntity getVehicleOwnerAndAccessRecordHistory(VehicleOwnerEntity vehicleOwnerEntity);

}