package com.tcgl.message.service;

/**
 * 邮件发送接口
 *
 * @author Shuguang_Liux
 * @date 2021/11/04 10:42
 */
public interface SendEmailService {

    /**
     * 发送文本
     *
     * @param subject     主题
     * @param content     内容
     * @param toWho       需要发送的人
     * @param ccPeoples   需要抄送的人
     * @param bccPeoples  需要密送的人
     * @param attachments 需要附带的附件
     */
    void sendSimpleMail(String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, String[] attachments);

    /**
     * 发送Html
     *
     * @param subject 主题
     * @param content 内容
     * @param toWho   需要发送的人
     */
    void sendHtmlMail(String subject, String content, String[] toWho);

}
