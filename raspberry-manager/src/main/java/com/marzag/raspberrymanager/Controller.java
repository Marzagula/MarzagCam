package com.marzag.raspberrymanager;

import com.marzag.raspberrymanager.dto.ReleaseHistory;
import com.marzag.raspberrymanager.util.FileManager;
import com.marzag.raspberrymanager.util.ProcessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/startCamera")
    public ResponseEntity startCamera() throws Exception {
        return ResponseEntity.ok(processManager.startCamera());
    }

    @GetMapping("/stopCamera")
    public ResponseEntity stopCamera() throws Exception {
        return ResponseEntity.ok(processManager.shutDownCamera());
    }

    @GetMapping("/restartCamera")
    public ResponseEntity restartCamera() throws Exception {
        return ResponseEntity.ok(processManager.restartCamera());
    }

    @GetMapping("/forceStopCamera")
    public ResponseEntity forceStopCamera() throws Exception {
        return ResponseEntity.ok(processManager.forceShutDownCamera());
    }



}
