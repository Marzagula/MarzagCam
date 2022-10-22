package com.marzag.raspberrymanager.util;

import com.marzag.raspberrymanager.dto.Camera;
import com.marzag.raspberrymanager.dto.ReleaseHistory;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

@Service
public class FileManager {

    private RestTemplate template = new RestTemplate();

    @Autowired
    EurekaHandler eurekaHandler;

    public ReleaseHistory loadNewestRelease() throws IOException {
        CameraProperties properties = new CameraProperties();
        String ownerId = properties.getProperty("camera.ownerId");
        String cameraId = properties.getProperty("camera.cameraId");

        Camera camera = template.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER")+"/getCameraByOwnerIdAndCameraId?ownerId="+ownerId+"&cameraId="+cameraId,Camera.class).getBody();
        ReleaseHistory releaseHistory = template.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER")+"/getNewestRelease",ReleaseHistory.class).getBody();
        byte [] byteFromFile = releaseHistory.getReleaseFile();
        try {
            String filePath = camera.getApplicationPath()+"/"+camera.getApplicationVersion();
            String newFilePath = camera.getApplicationPath()+"/"+releaseHistory.getReleaseName();
            File previousFile = new File(filePath);
            previousFile.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(newFilePath);
            fileOutputStream.write(byteFromFile);
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.setApplicationVersion(releaseHistory.getReleaseName());

        template.put(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER")+"/updateCamera",camera,Camera.class);
        return releaseHistory;
    }



}
