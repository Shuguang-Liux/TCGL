package com.tcgl.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcgl.serviceapi.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @package com.example.demo.dao
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/
@Mapper
@Repository(value = "UserDao")
public interface UserDao extends BaseMapper<UserEntity> {
}