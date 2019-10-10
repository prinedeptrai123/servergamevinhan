/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teso.framework.utils;

import com.teso.framework.common.Config;
import ga.pool.AbstractJob;
import ga.pool.WorkerPool;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author huynxt
 */
public class EmailUtils {

    private static WorkerPool workerPool = new WorkerPool();
    private static final String from = Config.getParam("mail_support", "account");// "taikhoan@xct.vn";
    private static final String password = Config.getParam("mail_support", "password");// "hotrotaikhoan";

    public static void sendMail(final String to, final String subject, final String body) {
        workerPool.deployJob(new AbstractJob() {

            @Override
            public void doJob() {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(from, password);
                    }
                });
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
                    message.setFrom(new InternetAddress(from));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject(subject, "UTF-8");

                    Multipart mp = new MimeMultipart();
                    MimeBodyPart htmlPart = new MimeBodyPart();
                    htmlPart.setContent(body, "text/html; charset=utf-8");
                    mp.addBodyPart(htmlPart);
                    message.setContent(mp);
                    Transport.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
