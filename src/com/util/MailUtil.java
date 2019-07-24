package com.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil implements Runnable {
    /**
     * 收件人信息
     */
    private String email;//收件人邮箱
    private String code;//激活码

    //初始化
    public MailUtil(String email, String code) {
        this.email = email;
        this.code = code;
    }

    /**
     * 线程
     */
    @Override
    public void run() {
        /**
         * 整体流程
         * 1.创建连接对象javax.mail.Session
         * 2.创建邮箱对象javax.mail.Message
         * 3.放松一封激活邮件
         */
        String from = "471887811@qq.com";
        String host = "smtp.qq.com";//指定发送邮件的主机smtp.126.com||smtp.163.com(网易)
        Properties properties = System.getProperties();//获取系统属性
        properties.setProperty("mail.smtp.host", host);//设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");//打开认证

        try {
            //QQ邮箱需要这段，163不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1.获取默认Session对向（创建连接对象，连接到邮箱服务器）
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮箱，密码不是真的密码，是个授权码
                    return new PasswordAuthentication("471887811@qq.com", "abcd123");
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("来自XX网站激活邮件");
            String content = "<html><head></head><body><h1>这是一封激活邮件，激活请点击一下链接</h1><h3><a href='http://localhost:8080/wectest4/active?code="
                    + code + "'>http://localhost:8080/webtest4/active?code=" + code + "</a></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");

            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
