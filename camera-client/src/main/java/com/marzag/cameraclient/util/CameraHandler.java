package com.marzag.cameraclient.util;

import com.marzag.cameraclient.enums.Exposure;
import com.marzag.cameraclient.exceptions.FailedToRunRaspistillException;
import com.marzag.cameraclient.tool.RPiCamera;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CameraHandler {

    public String takePhoto(String pictureName) throws FailedToRunRaspistillException, IOException, InterruptedException {
        if (System.getProperty("os.name").equals("Linux")) {
            RPiCamera rPiCamera = new RPiCamera("/home/MarzagCam/camera-client/captured-photos");
            rPiCamera.setWidth(1920).setHeight(1080)
                    .setBrightness(50)
                    .setExposure(Exposure.AUTO)
                    .setTimeout(2)
                    .setAddRawBayer(true);

            rPiCamera.takeStill(pictureName);
        } else
            pictureName = "Can't take photo on Windows";
        return pictureName;
    }
}
