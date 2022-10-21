package com.marzag.raspberrymanager;

import com.marzag.raspberrymanager.dto.ReleaseHistory;
import com.marzag.raspberrymanager.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    FileManager fileManager;


    @GetMapping("/getNewestRelease")
    public ResponseEntity getNewestRelease() throws IOException {
        ReleaseHistory release = fileManager.loadNewestRelease();
        return ResponseEntity.ok(release);
    }



}
