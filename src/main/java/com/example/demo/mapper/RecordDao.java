package com.example.demo.mapper;

import com.example.demo.entity.Record;

import java.util.List;

/**
 * @author Shuguang_Liux
 */
public interface RecordDao {
    /**
     * 主键删除
     * @param sourceId
     * @return
     */
    int deleteByPrimaryKey(Integer sourceId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer sourceId);

    int updateByPrimaryKeySelective(Record record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Record record);

    /**
     * 分页列表查询
     * @param record
     * @return
     */
    List<Record> selectInfoByPage(Record record);

    /**
     * 存在判断（车牌号为唯一）
     * @param licensePlate
     * @return
     */
    int isExistRecord(String licensePlate);
}