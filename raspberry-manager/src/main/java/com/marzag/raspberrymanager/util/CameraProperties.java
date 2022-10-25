package com.marzag.raspberrymanager.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CameraProperties extends Properties {

    public CameraProperties() {
        FileReader fileReader = null;
        try{
            fileReader = new FileReader("/home/MarzagCam/" + "raspberry-manager.config");
        } catch (FileNotFoundException ex) {
            try {
                String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                fileReader = new FileReader(rootPath + "application.properties");
            } catch (FileNotFoundException ex2) {
                ex2.printStackTrace();
            }
        }

        try {
            this.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
