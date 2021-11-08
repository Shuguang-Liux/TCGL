package com.tcgl.email.model;

import lombok.Data;

/**
 * 邮箱配置类
 *
 * @author Shuguang_Liux
 * @date 2021/11/04 13:02
 */
@Data
public class MailConfig {
    private String id;

    private String senderEmail;

    private String smtpAddress;

    private String port;

    private Integer enableStatus;

    private String password;

    private String nickname;

    private String remark;

}
