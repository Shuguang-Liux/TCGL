package com.tcgl.email.controller;

import com.tcgl.email.service.SendEmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件发送控制类
 *
 * @author Shuguang_Liux
 * @date 2021/11/04 10:25
 */
@RestController
@RequestMapping("sendEmail")
public class SendEmailController {

    @Resource
    private SendEmailService sendEmailService;

    @PostMapping( "send")
    public String sendEmail(){
        sendEmailService.sendSimpleMail("发送主题","发送内容",new String[]{"Shuguang_Liux@outlook.com"},null,null,null);
        return "1";
    }

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
    @PostMapping("sendSimpleMail")
    public void sendSimpleMail(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "toWho") String[] toWho,
            @RequestParam(value = "ccPeoples", required = false) String[] ccPeoples,
            @RequestParam(value = "bccPeoples", required = false) String[] bccPeoples,
            @RequestParam(value = "attachments", required = false) String[] attachments) {
        sendEmailService.sendSimpleMail(subject, content, toWho, ccPeoples, bccPeoples, attachments);
    }

    /**
     * 发送html邮件
     *
     * @param subject 主题
     * @param content 内容
     * @param toWho   谁
     */
    @PostMapping("sendHtmlMail")
    public void sendHtmlMail(String subject, String content, String[] toWho) {
        sendEmailService.sendHtmlMail(subject, content, toWho);
    }
}
