package com.chenyifaer.web.util;

import com.chenyifaer.web.config.EmailConfig;
import com.chenyifaer.web.constant.EmailConstant;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailUtil {

    public static void sendEmail(String emailTo,String emailTitle,String emailText) throws Exception {
        String emailFrom = EmailConfig.emailFrom;
        String emailFromKey = EmailConfig.emailFromKey;
        String emailHost = EmailConfig.emailHost;

        Properties prop = new Properties();
        //固定参数
        prop.setProperty(EmailConstant.MAIL_HOST, emailHost);
        prop.setProperty(EmailConstant.MAIL_TRANSPORT_PROTOCOL, EmailConstant.MAIL_TRANSPORT_PROTOCOL_VALUE);
        prop.setProperty(EmailConstant.MAIL_SMTP_AUTH, EmailConstant.MAIL_SMTP_AUTH_VALUE);

        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、使用邮箱的用户名和授权码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和授权码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(emailHost,emailFrom, emailFromKey);
        // 4、创建邮件
        Message message = createSimpleMail(session,emailFrom,emailTo,emailTitle,emailText);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * 发送邮件
     * @Author:wudh
     */
    public static MimeMessage createSimpleMail(
            Session session, String emailFrom,String emailTo, String emailTitle, String emailText) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(emailFrom));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        // 邮件的标题
        message.setSubject(emailTitle);
        // 邮件的文本内容
        message.setContent(emailText, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
