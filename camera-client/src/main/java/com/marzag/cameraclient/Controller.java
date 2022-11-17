package com.marzag.cameraclient;


import com.marzag.cameraclient.exceptions.FailedToRunRaspistillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    RaspberryService raspberryService;


    @GetMapping("/moveDetectionOn")
    public String test() throws InterruptedException {
        raspberryService.startDetecting();
        return "Detection mode turned on.";
    }

    @GetMapping("/moveDetectionOff")
    public String testStop() {
        raspberryService.stopDetecting();
        return "Detection mode turned off.";
    }

    @GetMapping("/takePhoto")
    public String takePhoto() throws InterruptedException, IOException, FailedToRunRaspistillException {
        return raspberryService.takePhoto();
    }

    @GetMapping("/cameraStatus")
    public String cameraStatus() {
        return "Camera is online.";
    }
}
