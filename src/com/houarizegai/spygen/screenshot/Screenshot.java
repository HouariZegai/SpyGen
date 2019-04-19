package com.houarizegai.spygen.screenshot;

import com.houarizegai.spygen.global.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Screenshot {

    /*
     Take screenshot and save it in folder mentioned in settings class
     with name passed in the parameter of function
      */
    public static void TakeScreenshot(String name) {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", new File(Settings.SCREENSHOT_PATH + "\\" + name + ".png"));
        } catch(AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}
