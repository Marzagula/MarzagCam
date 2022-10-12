package com.marzag.cameraserver;

import com.marzag.cameraserver.model.ReleaseHistory;
import com.marzag.cameraserver.repository.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    CameraService cameraService;


    @PostMapping("/createRelease")
    public void createRelease(@RequestBody String fileName){
        cameraService.newRelease(fileName);
    }

    @GetMapping("/getRelease")
    public ResponseEntity getRelease(){

        return ResponseEntity.ok(cameraService.getNewestRelease());
    }
}
