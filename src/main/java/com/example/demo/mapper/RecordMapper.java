package com.example.demo.mapper;

import com.example.demo.entity.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer sourceId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer sourceId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}