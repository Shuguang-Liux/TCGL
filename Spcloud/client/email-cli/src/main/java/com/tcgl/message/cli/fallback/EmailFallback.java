package com.tcgl.message.cli.fallback;

import com.tcgl.message.cli.EmailClient;
import org.springframework.stereotype.Service;


/**
 * @author Shuguang_Liux
 */
@Service
public class EmailFallback implements EmailClient {

    @Override
    public void sendSimpleMail(String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, String[] attachments) {
    }

    @Override
    public void sendHtmlMail(String subject, String content, String[] toWho) {
    }
}
