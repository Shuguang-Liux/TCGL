package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.AccessRecordEntity;
import com.example.demo.result.Result;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.service
 * @Description ToDo
 * @Date 2020/8/27 17:11
 **/

public interface AccessRecordService {
    /**
     * 信息存在性判断
     * @param licensePlate
     * @return
     */
    boolean isExistRecord(String licensePlate);

    /**
     * 列表查询
     * @param accessRecordEntity
     * @return
     */
    IPage<AccessRecordEntity> selectInfoByPage(Map<String,Object> params);

    /**
     * 信息插入
     * @param accessRecordEntity
     * @return
     */
    int insertRecordInfo(AccessRecordEntity accessRecordEntity);

    /**
     * 记录信息更新
     * @param accessRecordEntity
     * @return
     */
    int updateRecordInfo(AccessRecordEntity accessRecordEntity);

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
    AccessRecordEntity selectRecordByOne(String licensePlate);


}
