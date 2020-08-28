package com.example.demo.service.impl;

import com.example.demo.entity.Record;
import com.example.demo.mapper.RecordDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.result.Result;
import com.example.demo.service.RecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.service.impl
 * @Description ToDo
 * @Date 2020/8/27 17:11
 **/
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Autowired
    RecordService recordService;

    /**
     * 存在性判断
     * @param record
     * @return
     */
    @Override
    public boolean isExistRecord(Record record) {
        int count = recordDao.isExistRecord(record.getLicensePlate());
        return count>0 ? true : false;
    }

    /**
     * 列表查询
     * @param record
     * @return
     */
    @Override
    public List<Record> selectInfoByPage(Record record) {
        return recordDao.selectInfoByPage(record);
    }

    /**
     * 信息插入
     * @param record
     * @return
     */
    @Override
    public Result insertRecordInfo(Record record) {
        Result result = new Result();
        int count = recordDao.insertSelective(record);
        if (count == 1){
            result.setCode(200);
            result.setMessage("插入成功");
        }else {
            result.setCode(400);
            result.setMessage("插入失败");
        }
        return result;
    }

    /**
     * 记录信息更新
     * @param record
     * @return
     */
    @Override
    public Result updateRecordInfo(Record record) {
        Result result = new Result();
        int count = recordDao.updateByPrimaryKeySelective(record);
        if (count == 1){
            result.setCode(200);
            result.setMessage("插入成功");
        }else {
            result.setCode(400);
            result.setMessage("插入失败");
        }
        return result;
    }

    /**
     * 根据前台传递信息更新数据
     * @param record
     * @return
     */
    @Override
    public Result saveRecordByFront(Record record) {
        Result result = new Result();
        if (StringUtils.isBlank(record.getLicensePlate())){
            result.setCode(400);
            result.setMessage("输入参数不正确！");
            return result;
        }
        if (recordService.isExistRecord(record)){
            recordService.updateRecordInfo(record);
        }else {
            record.setEnterTime(new Date());
            recordService.insertRecordInfo(record);
        }

        return null;
    }
}
