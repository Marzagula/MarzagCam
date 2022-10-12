package com.marzag.cameraserver.repository;

import com.marzag.cameraserver.model.ReleaseHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
@EnableJpaRepositories
public class CameraService {

    @Autowired
    ReleaseHistoryRepository releaseHistoryRepository;

    String rootPath  = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public void newRelease(String fileName){
        if(fileName.isEmpty() || fileName.length() < 1)
            fileName = "camera-1.0-SNAPSHOT-jar-with-dependencies.jar";//w tym miejscu bedzie exception

        try {
            FileInputStream fis = new FileInputStream(rootPath + fileName);
            byte[] bytes = fis.readAllBytes();

            releaseHistoryRepository.save(new ReleaseHistory(fileName,bytes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReleaseHistory getNewestRelease(){
        return releaseHistoryRepository.findTopByOrderByIdDesc();
    }

}
