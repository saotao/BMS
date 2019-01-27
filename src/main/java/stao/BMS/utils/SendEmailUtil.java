package stao.BMS.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Slf4j
public class SendEmailUtil {

    public static int sendEmail(String[] tos,String sunject,String body) {
        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", ConfigProperties.emailHost);
        properties.put("mail.smtp.auth", "true");

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            log.error("com.sun.mail.util.MailSSLSocketFactory:",e);
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigProperties.emailFrom, ConfigProperties.emailToken); //发件人邮件用户名、授权码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(ConfigProperties.emailFrom));

            Address[] addresses = new Address[tos.length];
            int i=0;
            for(String to : tos){
                addresses[i] = new InternetAddress(to);
                i++;
            }
            // Set To: 头部头字段
            message.addRecipients(Message.RecipientType.TO,addresses);

            // Set Subject: 头部头字段
            message.setSubject(sunject);

            // 设置消息体
            message.setContent(body,"text/html;charset=utf-8");

            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            log.error("javax.mail.Transport.send(javax.mail.Message):",mex);
        }
        return 0;
    }

    public static void main(String[] args) {
        SendEmailUtil.sendEmail(new String[]{"18729030558@163.com","stao0246@qq.com","shentao6@guazi.com"},"你好","<p>qwiu12</>");
    }
}
