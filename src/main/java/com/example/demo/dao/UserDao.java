package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @package com.example.demo.dao
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
}