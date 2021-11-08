package com.tcgl.email.cli;

import com.tcgl.email.cli.fallback.EmailFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 电子邮件客户端
 *
 * @author Shuguang_Liux
 * @date 2021/11/08 14:05:54
 */
@FeignClient(name = "email", fallback = EmailFallback.class)
public interface EmailClient {

    /**
     * 发送简单的邮件
     *
     * @param subject     主题
     * @param content     内容
     * @param toWho       谁
     * @param ccPeoples   cc人民
     * @param bccPeoples  bcc人民
     * @param attachments 附件
     */
    @PostMapping("sendEmail/sendSimpleMail")
    void sendSimpleMail(@RequestParam(value = "subject") String subject,
                                @RequestParam(value = "content") String content,
                                @RequestParam(value = "toWho") String[] toWho,
                                @RequestParam(value = "ccPeoples", required = false) String[] ccPeoples,
                                @RequestParam(value = "bccPeoples", required = false) String[] bccPeoples,
                                @RequestParam(value = "attachments", required = false) String[] attachments);

    /**
     * 发送html邮件
     *
     * @param subject 主题
     * @param content 内容
     * @param toWho   谁
     */
    @PostMapping("sendEmail/sendHtmlMail")
    void sendHtmlMail(@RequestParam(value = "subject") String subject,
                              @RequestParam(value = "content") String content,
                              @RequestParam(value = "toWho") String[] toWho);
}
