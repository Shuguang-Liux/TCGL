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
     * @param record
     * @return
     */
    boolean isExistRecord(Record record);

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
    Result insertRecordInfo(Record record);

    /**
     * 记录信息更新
     * @param record
     * @return
     */
    Result updateRecordInfo(Record record);

    /**
     * 根据前台传递信息更新数据
     * @param record
     * @return
     */
    Result saveRecordByFront(Record record);
}
