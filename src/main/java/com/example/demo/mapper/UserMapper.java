package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @package com.example.demo.mapper
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/15 13:52
 **/
//@Mapper
//@Repository
public interface UserMapper {
    UserEntity getInfo(String userName, String userPassword);
}
