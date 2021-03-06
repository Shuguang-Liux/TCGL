package com.record.tcgl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.record.tcgl.entity.AccessRecordHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @package com.example.demo.dao
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/
@Mapper
@Repository("accessRecordHistoryDao")
public interface AccessRecordHistoryDao extends BaseMapper<AccessRecordHistoryEntity> {
}