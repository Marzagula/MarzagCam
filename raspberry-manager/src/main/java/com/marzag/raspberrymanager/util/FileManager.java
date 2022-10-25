package com.marzag.raspberrymanager.util;

import com.marzag.raspberrymanager.dto.Camera;
import com.marzag.raspberrymanager.dto.ReleaseHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.util.annotation.Nullable;

import java.io.*;

@Service
public class FileManager {

    private RestTemplate template = new RestTemplate();

    @Autowired
    EurekaHandler eurekaHandler;


    public ReleaseHistory loadNewestRelease() throws IOException {
        CameraProperties properties = new CameraProperties();
        String ownerId = properties.getProperty("camera.ownerId");
        String cameraId = properties.getProperty("camera.cameraId");

        Camera camera = template.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER") + "/getCameraByOwnerIdAndCameraId?ownerId=" + ownerId + "&cameraId=" + cameraId, Camera.class).getBody();
        ReleaseHistory releaseHistory = template.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER") + "/getNewestRelease", ReleaseHistory.class).getBody();
        byte[] byteFromFile = releaseHistory.getReleaseFile();
        try {
            String filePath = camera.getApplicationPath() + "/" + camera.getApplicationVersion();
            String newFilePath = camera.getApplicationPath() + "/" + releaseHistory.getReleaseName();
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

        template.put(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER") + "/updateCamera", camera, Camera.class);
        return releaseHistory;
    }


    public void logToExistingFile(String fileName, InputStream fileContent) {
        CameraProperties properties = new CameraProperties();
        String ownerId = properties.getProperty("camera.ownerId");
        String cameraId = properties.getProperty("camera.cameraId");

        Camera camera = template.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER") + "/getCameraByOwnerIdAndCameraId?ownerId=" + ownerId + "&cameraId=" + cameraId, Camera.class).getBody();
        String filePath = camera.getApplicationPath() + "/logs/" + fileName;
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileContent);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String outputString = "";

            String ln = "";
            while ((ln = bufferedReader.readLine()) != null) {
                outputString += ln + "\n";
            }
            fileOutputStream.write(outputString.getBytes());
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File createNewLogFile(String fileName) throws IOException {
        CameraProperties properties = new CameraProperties();
        String ownerId = properties.getProperty("camera.ownerId");
        String cameraId = properties.getProperty("camera.cameraId");

        Camera camera = template.getForEntity(eurekaHandler.urlFromFirstAvailableInstance("CAMERA-SERVER") + "/getCameraByOwnerIdAndCameraId?ownerId=" + ownerId + "&cameraId=" + cameraId, Camera.class).getBody();
        String filePath = camera.getApplicationPath() + "/logs/" + fileName;
        File file = new File(filePath);
        file.createNewFile();

        return file;
    }


}
