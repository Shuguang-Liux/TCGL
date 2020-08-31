package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
/**
 * 记录实体
 * @author Shuguang_Liux
 */
@Data
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer sourceId;

    /**
     * 车牌号
     */
    private String licensePlate;

    /**
     * 所属人名称
     */
    private String ownerName;

    /**
     * 进园时间
     */
    private Date enterTime;

    /**
     * 出园时间
     */
    private Date outTime;

    /**
     * 入园时长统计
     */
    private BigDecimal timeCount;

    /**
     * 价格
     */
    private BigDecimal billingPrice;

    /**
     * 是否出园
     */
    private String isOut;

    /**
     * 次数
     */
    private Integer accessTimes;

    /**
     * 删除状态默认N
     */
    private String deleteState;


}