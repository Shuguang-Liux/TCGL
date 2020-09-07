package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.AccessRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package com.example.demo.dao
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/3 16:45
**/
@Mapper
@Repository
public interface AccessRecordDao extends BaseMapper<AccessRecordEntity> {
}