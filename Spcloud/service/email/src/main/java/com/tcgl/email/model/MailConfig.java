package com.tcgl.email.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 邮箱配置类
 *
 * @author Shuguang_Liux
 * @date 2021/11/04 13:02
 */
@Data
@TableName("MAIL_CONFIG")
public class MailConfig {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Email(message = "请输入正确的邮箱地址")
    private String senderEmail;

    /**
     * 邮箱服务器
     */
    @NotNull(message = "邮箱服务器地址不能为空")
    private String smtpAddress;

    /**
     * 端口
     */
    @NotNull(message = "端口不能为空")
    private String port;

    /**
     * 有效状态
     */
    @Size(max = 1, message = "状态不正确")
    private Integer enableStatus;

    /**
     * 邮箱密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 真实姓名
     */
    private String nickname;

    /**
     * 备注
     */
    private String remark;

}
