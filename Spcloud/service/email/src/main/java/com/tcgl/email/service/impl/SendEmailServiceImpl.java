package com.tcgl.email.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tcgl.email.dao.MailConfigMapper;
import com.tcgl.email.model.MailConfig;
import com.tcgl.email.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * 邮件发送实现类
 *
 * @author Shuguang_Liux
 * @date 2021/11/04 10:43
 */
@Service
@Slf4j
public class SendEmailServiceImpl implements SendEmailService {


    //默认编码
    public static final String DEFAULT_ENCODING = "UTF-8";

    //本身邮件的发送者，来自邮件配置
//    @Value("${spring.mail.username}")
//    private String userName;
//    @Value("${spring.mail.nickname}")
//    private String nickname;
//
//    @Resource
//    private JavaMailSender javaMailSender;

    @Resource
    private MailConfigMapper mailConfigMapper;





    /**
     * 邮件配置进行主动配置(实现动态修改,yml文件不再进行配置)
     *
     * @param mailConfig 邮件配置
     * @return {@link JavaMailSenderImpl}
     */
    private JavaMailSenderImpl config(MailConfig mailConfig) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailConfig.getSmtpAddress());
        javaMailSender.setUsername(mailConfig.getSenderEmail());
        javaMailSender.setPassword(mailConfig.getPassword());
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.socketFactory.port", mailConfig.getPort());
        javaMailProperties.put("mail.smtp.port", mailConfig.getPort());
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }


    @Override
    public void sendSimpleMail(String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, String[] attachments) {
        //检验参数：邮件主题、收件人、邮件内容必须不为空才能够保证基本的逻辑执行
        if (ObjectUtil.hasNull(subject, toWho, content) || toWho.length == 0) {
            log.error("邮件-> {} 无法继续执行，因为缺少基本的参数：邮件主题、收件人、邮件内容", subject);
            throw new RuntimeException("模板邮件无法继续发送，因为缺少必要的参数！");
        }
        MailConfig mailConfig = mailConfigMapper.sendMailConfig();
        JavaMailSenderImpl javaMailSender = this.config(mailConfig);
        log.info("开始发送简单文本邮件：主题->{}，收件人->{}，抄送人->{}，密送人->{}，附件->{}", subject, toWho, ccPeoples, bccPeoples, attachments);
        String from = mailConfig.getNickname() + '<' + mailConfig.getSenderEmail() + '>';
        //附件处理，需要处理附件时，需要使用二进制信息，使用 MimeMessage 类来进行处理
        if (attachments != null && attachments.length > 0) {
            try {
                //附件处理需要进行二进制传输
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, DEFAULT_ENCODING);
                //设置邮件的基本信息：这些函数都会在后面列出来
                boolean continueProcess = this.handleBasicInfo(helper, from, subject, content, toWho, ccPeoples, bccPeoples, false);
                //如果处理基本信息出现错误
                if (!continueProcess) {
                    log.error("邮件基本信息出错: 主题->{}", subject);
                    return;
                }
                //处理附件
                this.handleAttachment(helper, subject, attachments);
                //发送该邮件
                javaMailSender.send(mimeMessage);
                log.info("发送邮件成功: 主题->{}", subject);
            } catch (MessagingException e) {
                e.printStackTrace();
                log.error("发送邮件失败: 主题->{}", subject);

            }
        } else {
            //创建一个简单邮件信息对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置邮件的基本信息
            this.handleBasicInfo(simpleMailMessage, from, subject, content, toWho, ccPeoples, bccPeoples);
            //发送邮件
            javaMailSender.send(simpleMailMessage);
            log.info("发送邮件成功: 主题->{}", subject, toWho, ccPeoples, bccPeoples, attachments);
        }
    }

    @Override
    public void sendHtmlMail(String subject, String content, String[] toWho) {

        //检验参数：邮件主题、收件人、邮件内容必须不为空才能够保证基本的逻辑执行
        if (ObjectUtil.hasNull(subject, toWho, content) || toWho.length == 0) {
            log.error("邮件-> {} 无法继续执行，因为缺少基本的参数：邮件主题、收件人、邮件内容", subject);
            throw new RuntimeException("模板邮件无法继续发送，因为缺少必要的参数！");
        }
        MailConfig mailConfig = mailConfigMapper.sendMailConfig();
        JavaMailSenderImpl javaMailSender = this.config(mailConfig);
        log.info("开始发送Html邮件：主题->{}，收件人->{}", subject, toWho);
        String from = mailConfig.getNickname() + '<' + mailConfig.getSenderEmail() + '>';
        //html
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, DEFAULT_ENCODING);
            //设置邮件的基本信息
            handleBasicInfo(helper, from, subject, content, toWho);
            //发送邮件
            javaMailSender.send(mimeMessage);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送邮件出错->{}", subject);
        }
        log.info("发送邮件成功: 主题->{}", subject, toWho);
    }


    /**
     * 处理二进制邮件的基本信息，比如需要带附件的文本邮件、HTML文件、图片邮件、模板邮件等等
     *
     * @param mimeMessageHelper：二进制文件的包装类
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param ccPeoples：抄送人
     * @param bccPeoples：暗送人
     * @param isHtml：是否是HTML文件，用于区分带附件的简单文本邮件和真正的HTML文件
     * @return ：返回这个过程中是否出现异常，当出现异常时会取消邮件的发送
     */
    private boolean handleBasicInfo(MimeMessageHelper mimeMessageHelper, String from, String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, boolean isHtml) {
        try {
            //设置必要的邮件元素
            //设置发件人
            mimeMessageHelper.setFrom(from);
            //设置邮件的主题
            mimeMessageHelper.setSubject(subject);
            //设置邮件的内容，区别是否是HTML邮件
            mimeMessageHelper.setText(content, isHtml);
            //设置邮件的收件人
            mimeMessageHelper.setTo(toWho);
            //设置非必要的邮件元素，在使用helper进行封装时，这些数据都不能够为空
            if (ccPeoples != null)
            //设置邮件的抄送人：MimeMessageHelper # Assert.notNull(cc, "Cc address array must not be null");
            {
                mimeMessageHelper.setCc(ccPeoples);
            }

            if (bccPeoples != null)
            //设置邮件的密送人：MimeMessageHelper # Assert.notNull(bcc, "Bcc address array must not be null");
            {
                mimeMessageHelper.setBcc(bccPeoples);
            }
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件基本信息出错->{}", subject);
        }
        return false;
    }


    /**
     * 用于填充简单文本邮件的基本信息
     *
     * @param simpleMailMessage：文本邮件信息对象
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param ccPeoples：抄送人
     * @param bccPeoples：暗送人
     */
    private void handleBasicInfo(SimpleMailMessage simpleMailMessage, String from, String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples) {
        //设置发件人
        simpleMailMessage.setFrom(from);
        //设置邮件的主题
        simpleMailMessage.setSubject(subject);
        //设置邮件的内容
        simpleMailMessage.setText(content);
        //设置邮件的收件人
        simpleMailMessage.setTo(toWho);
        //设置邮件的抄送人
        simpleMailMessage.setCc(ccPeoples);
        //设置邮件的密送人
        simpleMailMessage.setBcc(bccPeoples);
    }

    /**
     * 发送html
     *
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param mimeMessageHelper：二进制文件的包装类
     */

    private void handleBasicInfo(MimeMessageHelper mimeMessageHelper, String from, String subject, String content, String[] toWho) {
        try {
            //设置发件人
            mimeMessageHelper.setFrom(from);
            //设置邮件的主题
            mimeMessageHelper.setSubject(subject);
            //设置邮件的内容
            mimeMessageHelper.setText(content, true);
            //设置邮件的收件人
            mimeMessageHelper.setTo(toWho);
        } catch (MessagingException e) {
            log.error("html邮件基本信息出错->{}", subject);
        }
    }

    /**
     * 用于处理附件信息，附件需要 MimeMessage 对象
     *
     * @param mimeMessageHelper：处理附件的信息对象
     * @param subject：邮件的主题，用于日志记录
     * @param attachmentFilePaths：附件文件的路径，该路径要求可以定位到本机的一个资源
     */
    private void handleAttachment(MimeMessageHelper mimeMessageHelper, String subject, String[] attachmentFilePaths) {
        //判断是否需要处理邮件的附件
        if (attachmentFilePaths != null && attachmentFilePaths.length > 0) {
            FileSystemResource resource;
            String fileName;
            //循环处理邮件的附件
            for (String attachmentFilePath : attachmentFilePaths) {
                //获取该路径所对应的文件资源对象
                resource = new FileSystemResource(new File(attachmentFilePath));
                //判断该资源是否存在，当不存在时仅仅会打印一条警告日志，不会中断处理程序。
                // 也就是说在附件出现异常的情况下，邮件是可以正常发送的，所以请确定你发送的邮件附件在本机存在
                if (!resource.exists()) {
                    log.warn("邮件->{} 的附件->{} 不存在！", subject, attachmentFilePath);
                    //开启下一个资源的处理
                    continue;
                }
                //获取资源的名称
                fileName = resource.getFilename();
                try {
                    //添加附件
                    mimeMessageHelper.addAttachment(fileName, resource);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    log.error("邮件->{} 添加附件->{} 出现异常->{}", subject, attachmentFilePath, e.getMessage());
                }
            }
        }
    }

}
