package com.marzag.cameraclient;

import com.marzag.cameraclient.enums.Exposure;
import com.marzag.cameraclient.util.RPiCamera;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class RaspberryService {

    public String takePhoto() throws com.hopding.jrpicam.exceptions.FailedToRunRaspistillException, IOException, InterruptedException {
        RPiCamera rPiCamera = new RPiCamera("/home/MarzagCam/camera-client/captured-photos");
        rPiCamera.setWidth(1920).setHeight(1080)
                .setBrightness(50)
                .setExposure(Exposure.AUTO)
                .setTimeout(2)
                .setAddRawBayer(true);

        String pictureName=new Date().toString()+".jpeg";
        rPiCamera.takeStill(pictureName);
        return pictureName;
    }

}
