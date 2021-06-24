package com.tcgl.serviceapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
    * 入园记录表
 * @author Shuguang_Liux
 */
@Data
@TableName(value = "t_access_record")
public class AccessRecordEntity implements Serializable{
    private static final long serialVersionUID = -8700173692257274464L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌号
     */
    @TableField(value = "license_plate")
    private String licensePlate;

    /**
     * 所属人名称
     */
    @TableField(value = "owner_name")
    private String ownerName;

    /**
     * 进园时间
     */
    @TableField(value = "enter_time")
    private Date enterTime;

    /**
     * 出园时间
     */
    @TableField(value = "out_time")
    private Date outTime;

    /**
     * 入园时长统计
     */
    @TableField(value = "time_count")
    private BigDecimal timeCount;

    /**
     * 价格
     */
    @TableField(value = "billing_price")
    private BigDecimal billingPrice;

    /**
     * 是否出园
     */
    @TableField(value = "is_out")
    private String isOut;

    /**
     * 次数
     */
    @TableField(value = "access_times")
    private Integer accessTimes;

    /**
     * 删除状态默认N
     */
    @TableField(value = "delete_status")
    private String deleteStatus;

    /**
     * 是否预付费用户（N为非预付费用户，Y为预付费用户）
     */
    @TableField(value = "is_prepayment")
    private String isPrepayment;


    public static final String COL_SOURCE_ID = "source_id";

    public static final String COL_LICENSE_PLATE = "license_plate";

    public static final String COL_OWNER_NAME = "owner_name";

    public static final String COL_ENTER_TIME = "enter_time";

    public static final String COL_OUT_TIME = "out_time";

    public static final String COL_TIME_COUNT = "time_count";

    public static final String COL_BILLING_PRICE = "billing_price";

    public static final String COL_IS_OUT = "is_out";

    public static final String COL_ACCESS_TIMES = "access_times";

    public static final String COL_DELETE_STATUS = "delete_status";

    public static final String COL_IS_PREPAYMENT = "is_prepayment";
}