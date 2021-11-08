package com.tcgl.email.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcgl.email.model.MailConfig;

/**
 * 邮件配置
 *
 * @author Shuguang_Liux
 * @date 2021/11/04 14:15
 */
public interface MailConfigMapper extends BaseMapper<MailConfigMapper> {
    /**
     * 发送邮件配置
     *
     * @return {@link MailConfig}
     */
    MailConfig sendMailConfig();
}
