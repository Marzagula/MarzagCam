package com.marzag.raspberrymanager.util;

import com.marzag.raspberrymanager.model.ReleaseHistory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileManager {

    private RestTemplate template = new RestTemplate();

    /*dodac konfiguracje sciezki, obsluzyc exceptiony*/
    public void loadNewestRelease() {
        ReleaseHistory releaseHistory = template.getForEntity("http://localhost:8083/getRelease",ReleaseHistory.class).getBody();
        byte [] byteFromFile = releaseHistory.getReleaseFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\smietnik\\"+releaseHistory.getReleaseName());
            fileOutputStream.write(byteFromFile);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
