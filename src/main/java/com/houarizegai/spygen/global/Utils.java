package com.houarizegai.spygen.global;

import com.houarizegai.spygen.keylogger.KeyStorage;

import java.util.List;

public class Utils {

    public static String rawPrint(List<KeyStorage> keyStorageList) {
        if(keyStorageList.isEmpty())
            return "Nothing has been pressed.";

        StringBuilder data = new StringBuilder();
        for(KeyStorage key : keyStorageList)
            data.append(key).append(System.lineSeparator());

        return data.toString();
    }
}
