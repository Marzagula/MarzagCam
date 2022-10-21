package com.marzag.cameraserver;

import com.marzag.cameraserver.model.Camera;
import com.marzag.cameraserver.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class Controller {

    @Autowired
    ReleaseService releaseService;


    @PostMapping("/createRelease")
    public ResponseEntity createRelease(@RequestBody String fileName) throws FileNotFoundException {
        return ResponseEntity.ok(releaseService.newRelease(fileName));
    }

    @GetMapping("/getNewestRelease")
    public ResponseEntity getRelease(){
        return ResponseEntity.ok(releaseService.getNewestRelease());
    }

    @GetMapping("/getRelease")
    public ResponseEntity getRelease(@RequestParam String releaseName){
        return ResponseEntity.ok(releaseService.getRelease(releaseName));
    }

    @GetMapping("/getCameraByOwnerIdAndCameraId")
    public ResponseEntity getCameraByOwnerIdAndCameraId(@RequestParam String ownerId,@RequestParam String cameraId){
        return ResponseEntity.ok(releaseService.getCameraByOwnerIdAndCameraId(ownerId, cameraId));
    }

    @PutMapping("/updateCamera")
    public ResponseEntity updateCamera(@RequestBody Camera camera){
        return ResponseEntity.ok(releaseService.updateCamera(camera));
    }
}
