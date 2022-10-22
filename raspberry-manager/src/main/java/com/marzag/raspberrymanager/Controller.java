package com.marzag.raspberrymanager;

import com.marzag.raspberrymanager.dto.ReleaseHistory;
import com.marzag.raspberrymanager.util.FileManager;
import com.marzag.raspberrymanager.util.ProcessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    FileManager fileManager;

    @Autowired
    ProcessManager processManager;


    @GetMapping("/getNewestRelease")
    public ResponseEntity getNewestRelease() throws IOException {
        ReleaseHistory release = fileManager.loadNewestRelease();
        return ResponseEntity.ok(release);
    }

    @GetMapping("/stopCamera")
    public ResponseEntity stopCamera(){
        return null;
    }

    @GetMapping("/startCamera")
    public ResponseEntity startCamera() throws IOException {
        return ResponseEntity.ok(processManager.startCamera());
    }

    @GetMapping("/restartCamera")
    public ResponseEntity restartCamera(){
        return null;
    }

}
