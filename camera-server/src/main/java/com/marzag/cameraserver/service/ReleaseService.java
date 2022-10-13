package com.marzag.cameraserver.service;

import com.marzag.cameraserver.model.Camera;
import com.marzag.cameraserver.model.ReleaseHistory;

import com.marzag.cameraserver.repository.CameraRepository;
import com.marzag.cameraserver.repository.ReleaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@EnableJpaRepositories("com.marzag.cameraserver.repository")
public class ReleaseService {

    @Autowired
    ReleaseHistoryRepository releaseHistoryRepository;

    @Autowired
    CameraRepository cameraRepository;

    String rootPath  = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public ReleaseHistory newRelease(String fileName) throws FileNotFoundException {
        if(fileName.trim().isEmpty() || fileName.length() < 1)
            throw new FileNotFoundException("File name can't be null");
        try {
            FileInputStream fis = new FileInputStream(rootPath + fileName);
            byte[] bytes = fis.readAllBytes();

            releaseHistoryRepository.save(new ReleaseHistory(fileName,bytes));
            return getRelease(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ReleaseHistory getNewestRelease(){
        return releaseHistoryRepository.findTopByOrderByIdDesc();
    }
    public ReleaseHistory getRelease(String releaseName){
        return releaseHistoryRepository.findReleaseHistoryByReleaseName(releaseName);
    }

    public Camera getCameraByOwnerIdAndCameraId(String ownerId, String cameraId){
        return cameraRepository.getCameraByOwnerIdAndCameraId(ownerId,cameraId);
    }
    public Camera updateCamera(Camera camera){
        return cameraRepository.save(camera);
    }
}
