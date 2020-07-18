package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    UserEntity getInfo(String userName,String userPassword);
}