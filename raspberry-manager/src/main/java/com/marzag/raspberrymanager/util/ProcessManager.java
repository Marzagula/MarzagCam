package com.marzag.raspberrymanager.util;

import com.marzag.raspberrymanager.RaspberryManagerApplication;
import com.marzag.raspberrymanager.dto.Camera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@Service
public class ProcessManager {
    
    @Autowired
    EurekaHandler eurekaHandler;

    @Autowired
    FileManager fileManager;

    RestTemplate restTemplate = new RestTemplate();

    long cameraClientPID = -1l;

    public String killProcess(long PID) throws IOException {

        String cmd = "taskkill /F /PID " + PID;
        Runtime.getRuntime().exec(cmd);
        cameraClientPID = -1l;
        return "Process has been killed.";
    }

    public Process runExecutableJar(String path, String jarName) throws IOException {

        ProcessBuilder procBuild = new ProcessBuilder(
                "java", "-jar", path + "/" + jarName);

        //creating output log for this process, they're redirected from application and placed in <camera-client application path>/logs directory
        String logFileName = jarName.replace(".jar", "") + ".log";
        File processOutput = fileManager.createNewLogFile(logFileName);
        procBuild.redirectOutput(processOutput);
        fileManager.logToExistingFile(logFileName, new FileInputStream(processOutput));

        Process p = procBuild.start();
        cameraClientPID = p.pid();
        return p;
    }

    public String startCamera() throws Exception {
        if (cameraClientPID == -1l) {
            CameraProperties properties = new CameraProperties();
            String ownerId = properties.getProperty("camera.ownerId");
            String cameraId = properties.getProperty("camera.cameraId");

            Camera camera = restTemplate.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER") + "/getCameraByOwnerIdAndCameraId?ownerId=" + ownerId + "&cameraId=" + cameraId, Camera.class).getBody();
            Process process = runExecutableJar(camera.getApplicationPath(), camera.getApplicationVersion());
        } else throw new Exception("Camera is already on.");

        return "Started Camera client.";
    }

    public String shutDownCamera() throws Exception {
        if (cameraClientPID == -1l)
            throw new Exception("Camera is already turned off.");
        return killProcess(cameraClientPID);
    }

    public String restartCamera() throws Exception {
        if (cameraClientPID != -1)
            shutDownCamera();
        startCamera();
        return "Camera has been restarted.";
    }
}
