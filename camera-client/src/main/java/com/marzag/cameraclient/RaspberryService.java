package com.marzag.cameraclient;

import com.marzag.cameraclient.exceptions.FailedToRunRaspistillException;
import com.marzag.cameraclient.util.CameraHandler;
import com.marzag.cameraclient.util.DetectorRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class RaspberryService {

    @Autowired
    CameraHandler cameraHandler;

    DetectorRunnable runnable;
    Thread thread;


    public void stopDetecting() {
        runnable.stop();
    }


    public String takePhoto() throws FailedToRunRaspistillException, IOException, InterruptedException {
        String pictureName = "Manual " + new Date().toString() + ".jpeg";
        cameraHandler.takePhoto(pictureName);
        return pictureName;
    }

    public void startDetecting() {
        runnable = new DetectorRunnable();
        thread = new Thread(runnable);
        runnable.start();
        thread.start();
    }


}
