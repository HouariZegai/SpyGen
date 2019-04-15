package com.houarizegai.spygen.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SendMails {
    private static String senderMail;
    private static String senderPassword;

    // Message sending information
    private List<String> msgRecipients;
    private String msgSubject;
    private String msgContent;
    private String msgType;

    public SendMails() {
        this.msgType = "text/html";
        msgRecipients = new LinkedList<>();
    }

    /* Start sender information */

    public SendMails setSenderMail(String senderMail) {
        this.senderMail = senderMail;
        return this;
    }

    public SendMails setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
        return this;
    }

    /* End sender information */

    /* Start message information */

    public SendMails setMessageRecipients(List<String> msgRecipients) {
        this.msgRecipients = msgRecipients;
        return this;
    }

    public SendMails setMessageRecipients(String... msgRecipients) {
        this.msgRecipients.addAll(Arrays.asList(msgRecipients));
        return this;
    }

    public SendMails setMessageSubject(String msgSubject) {
        this.msgSubject = msgSubject;
        return this;
    }

    public SendMails setMessageContent(String msgContent) {
        this.msgContent = msgContent;
        return this;
    }

    public SendMails setMessageType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    /* End message information */

    public void send() {
        System.out.println("Prepare sending msg ...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, senderPassword);
            }
        });

        Message msg = prepareMessage(session);

        try {
            Transport.send(msg);
            System.out.println("Message send successfully !");
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    private Message prepareMessage(Session session) {
        Message msg = null;
        try {
            msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderMail));

            InternetAddress[] recipientAddress = new InternetAddress[msgRecipients.size()];
            for(int i = 0; i < msgRecipients.size(); i++)
                recipientAddress[i] = new InternetAddress(msgRecipients.get(i));
            msg.setRecipients(Message.RecipientType.TO, recipientAddress);

            msg.setSubject(msgSubject);
            msg.setContent(msgContent, msgType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
