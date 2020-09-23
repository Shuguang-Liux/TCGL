package com.record.tcgl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.record.tcgl.entity.AccessRecordEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

/**
 * @package com.example.demo.dao
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/3 16:45
**/
@Mapper
@Repository
public interface AccessRecordDao extends BaseMapper<AccessRecordEntity> {

    /**
     * 根据licensePlateSet结果集取值
     * @param licensePlateSet
     * @return
     */
    @MapKey("licensePlate")
    Map<String,AccessRecordEntity> getAccessRecordByLicensePlateSet(@Param("licensePlateSet") Set<String> licensePlateSet);
}