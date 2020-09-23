package com.record.tcgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @package com.example.demo.entity
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/3 14:00
**/

/**
    * 预付用户钱款信息记录表
 * @author Shuguang_Liux
 */
@Data
@TableName(value = "t_payment_record")
public class PaymentRecordEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 缴费状态，1为正常，2为超期，3为放弃缴费
     */
    @TableField(value = "payment_status")
    private String paymentStatus;

    /**
     * 缴费时间
     */
    @TableField(value = "payment_date")
    private Date paymentDate;

    /**
     * 缴费金额
     */
    @TableField(value = "payment_amount")
    private int paymentAmount;

    /**
     * 缴费方式
     */
    @TableField(value = "payment_method")
    private String paymentMethod;

    /**
     * 到期时间
     */
    @TableField(value = "expiration_time")
    private Date expirationTime;

    /**
     * 关联车辆所有人表主键
     */
    @TableField(value = "owner_id")
    private Integer ownerId;


    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PAYMENT_STATUS = "payment_status";

    public static final String COL_PAYMENT_DATE = "payment_date";

    public static final String COL_PAYMENT_AMOUNT = "payment_amount";

    public static final String COL_PAYMENT_METHOD = "payment_method";

    public static final String COL_EXPIRATION_TIME = "expiration_time";

    public static final String COL_OWNER_ID = "owner_id";
}