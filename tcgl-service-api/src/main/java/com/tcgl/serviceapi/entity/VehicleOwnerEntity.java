package com.tcgl.serviceapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.entity
 * @Description 车辆所有人信息表
 * @Date 2020/9/4 10:16
 **/
@Data
@EntityScan
@TableName(value = "t_vehicle_owner")
public class VehicleOwnerEntity implements Serializable {
    private static final long serialVersionUID = 1400920817912195122L;
    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车辆牌照
     */
    @TableField(value = "license_plate")
    private String licensePlate;

    /**
     * 车辆所有人
     */
    @TableField(value = "vehicle_owner")
    private String vehicleOwner;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_person")
    private String createPerson;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_person")
    private String updatePerson;

    /**
     * 删除状态
     */
    @TableField(value = "delete_status")
    private String deleteStatus;

    /**
     * 有效状态
     */
    @TableField(value = "is_valid")
    private String isValid;

    /**
     * 历史列表
     */
    @TableField(exist = false)
    private List<AccessRecordHistoryEntity> accessRecordHistoryEntityList;

    public static final String COL_ID = "id";

    public static final String COL_LICENSE_PLATE = "license_plate";

    public static final String COL_VEHICLE_OWNER = "vehicle_owner";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_PERSON = "create_person";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_PERSON = "update_person";

    public static final String COL_DELETE_STATUS = "delete_status";

    public static final String COL_IS_VALID = "is_valid";
}