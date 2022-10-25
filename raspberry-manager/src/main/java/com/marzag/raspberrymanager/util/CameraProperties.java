package com.marzag.raspberrymanager.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CameraProperties extends Properties {

    public CameraProperties() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(rootPath + "application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
