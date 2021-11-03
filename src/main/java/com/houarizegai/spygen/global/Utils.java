package com.houarizegai.spygen.global;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void deleteFolderContent(final File folder) {
        File[] files = folder.listFiles();
        if (files == null)
            return;

        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                deleteFolderContent(fileEntry);
            } else {
                fileEntry.delete();
            }
        }
    }

    public static void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 10; i++)
            System.out.println();
    }
}
