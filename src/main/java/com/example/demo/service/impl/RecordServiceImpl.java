package com.example.demo.service.impl;

import com.example.demo.entity.Record;
import com.example.demo.mapper.RecordDao;
import com.example.demo.result.ApplyIntEnum;
import com.example.demo.result.ApplyStringEnum;
import com.example.demo.result.Result;
import com.example.demo.service.RecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.service.impl
 * @Description ToDo
 * @Date 2020/8/27 17:11
 **/
@Service("RecordService")
public class RecordServiceImpl implements RecordService{

    @Autowired
    RecordDao recordDao;

    @Autowired
    RecordService recordService;


    /**
     * 存在性判断
     * @param licensePlate
     * @return
     */
    @Override
    public boolean isExistRecord(String licensePlate) {
        int count = recordDao.isExistRecord(licensePlate);
        return count > 0;
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
    public int insertRecordInfo(Record record) {
        return recordDao.insertSelective(record);

    }

    /**
     * 记录信息更新
     * @param record
     * @return
     */
    @Override
    public int updateRecordInfo(Record record) {
        return recordDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 车牌扫描入口信息更新（车牌传入）
     * @param licensePlate
     * @return
     */
    @Override
    public Result<String> saveRecordByFront(String licensePlate) {
        Result<String> resultVo = new Result<>();
        Record record = new Record();
        //非空判断
        if (StringUtils.isBlank(licensePlate)){
            resultVo.setError(200,"传入参数为空");
            return resultVo;
        }
        int count = 0;
        //车牌存在性判断
        if (recordService.isExistRecord(licensePlate)){
            //取值
            record = recordService.selectRecordByOne(licensePlate);
            //获取枚举值
            String isOutValue = ApplyStringEnum.ISOUT.getStringValue();
            //是否已经出园判断
            if (isOutValue.equals(record.getIsOut())){
                //未出园
                Map<String,BigDecimal> map = priceCalculation(record.getEnterTime(),new Date());
                //赋值时间统计
                record.setTimeCount(map.get("timeCount"));
                //赋值价格统计
                record.setBillingPrice(map.get("billingPrice"));
                //赋值出园时间
                record.setOutTime(new Date());
                //赋值出园状态
                record.setIsOut("Y");
            }else {
                //入场时间为当前时间
                record.setEnterTime(new Date());
                //出园时间，价格，时间统计置为空
                record.setOutTime(null);
                record.setBillingPrice(null);
                record.setTimeCount(null);
                //是否出园置为N
                record.setIsOut("N");
                //入园次数+1
                record.setAccessTimes(record.getAccessTimes()+1);
            }
            //更新表数据
             count = recordService.updateRecordInfo(record);
        }else {
            //不存在即插入
            record.setEnterTime(new Date());
            count = recordService.insertRecordInfo(record);
        }
        if (count>0){
            resultVo.setMessage("数据库操作成功");
        }else {
            resultVo.setError(400,"操作失败");
        }
        return resultVo;

    }

    /**
     * 信息取数据
     * @param licensePlate
     * @return
     */
    @Override
    public Record selectRecordByOne(String licensePlate) {
        return recordDao.selectRecordByOne(licensePlate);
    }

    /**
     * 用户时长计算
     * @param enterTime
     * @param outTime
     * @return
     */
    private Map<String,BigDecimal> priceCalculation(Date enterTime, Date outTime){
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        //计算时间差值
        double dateCount = outTime.getTime()-enterTime.getTime();
        //计算时间长短（小时）毫秒级单位
        double hours = dateCount/(60*60);
        //向上取整
        BigDecimal timeCount = BigDecimal.valueOf(Math.ceil(hours));
        //价格计数(Bigdecimal乘法multiply)
        BigDecimal price = timeCount.multiply(BigDecimal.valueOf(ApplyIntEnum.PRICE.getIntValue()));
        //赋值
        map.put("timeCount",timeCount);
        map.put("billingPrice",price);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(ApplyIntEnum.PRICE.getIntValue());
        System.out.println(ApplyStringEnum.ISOUT.getStringValue());
    }
}
