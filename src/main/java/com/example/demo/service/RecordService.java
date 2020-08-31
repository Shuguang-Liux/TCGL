package com.example.demo.service;

import com.example.demo.entity.Record;
import com.example.demo.result.Result;

import java.util.List;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.service
 * @Description ToDo
 * @Date 2020/8/27 17:11
 **/

public interface RecordService {
    /**
     * 信息存在性判断
     * @param licensePlate
     * @return
     */
    boolean isExistRecord(String licensePlate);

    /**
     * 列表查询
     * @param record
     * @return
     */
    List<Record> selectInfoByPage(Record record);

    /**
     * 信息插入
     * @param record
     * @return
     */
    int insertRecordInfo(Record record);

    /**
     * 记录信息更新
     * @param record
     * @return
     */
    int updateRecordInfo(Record record);

    /**
     * 扫描入口信息更新
     * @param licensePlate
     * @return
     */
    Result saveRecordByFront(String licensePlate);

    /**
     * 更具信息取数据
     * @param licensePlate
     * @return
     */
    Record selectRecordByOne(String licensePlate);
}
