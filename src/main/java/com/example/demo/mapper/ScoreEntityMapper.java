package com.example.demo.mapper;

import com.example.demo.entity.ScoreEntity;

public interface ScoreEntityMapper {
    int deleteByPrimaryKey(Integer scoreId);

    int insert(ScoreEntity record);

    int insertSelective(ScoreEntity record);

    ScoreEntity selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(ScoreEntity record);

    int updateByPrimaryKey(ScoreEntity record);
}