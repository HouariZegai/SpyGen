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
}
