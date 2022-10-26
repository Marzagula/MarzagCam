package com.marzag.cameraclient;

import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    RaspberryService raspberryService;

    @GetMapping("/test")
    public String test(){
        return "Client is online.";
    }

    @GetMapping("takePhoto")
    public String takePhoto() throws InterruptedException, IOException, FailedToRunRaspistillException {
        return raspberryService.takePhoto();
    }
}
