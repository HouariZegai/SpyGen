package com.houarizegai.spygen.global;

import java.io.File;

public class Utils {

    public static void deleteFolderContent(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                deleteFolderContent(fileEntry);
            } else {
                fileEntry.delete();
            }
        }
    }

    public static boolean isValidMail(String mail) {
        if(mail == null)
            return false;

        return mail.trim().matches("[a-zA-Z_][\\w]*[-]{0,4}[\\w]+@[a-zA-Z0-9]+.[a-zA-Z]{2,6}");
    }
}
