package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @package com.example.demo.entity
 * @Description ToDo
 * @author Shuguang_Liux
 * @Date 2020/9/4 10:16
**/
/**
    * 车辆所有人登记表
    */
@Data
@TableName(value = "t_vehicle_owner")
public class VehicleOwnerEntity implements Serializable {
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
    @TableField(value = "delete_state")
    private String deleteState;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_LICENSE_PLATE = "license_plate";

    public static final String COL_VEHICLE_OWNER = "vehicle_owner";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_PERSON = "create_person";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_PERSON = "update_person";

    public static final String COL_DELETE_STATE = "delete_state";
}