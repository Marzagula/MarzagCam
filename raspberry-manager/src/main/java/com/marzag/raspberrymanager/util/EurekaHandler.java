package com.marzag.raspberrymanager.util;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EurekaHandler {

    @Autowired
    EurekaClient eurekaClient;

    public String urlFromFirstAvailableInstance(String appName) {

        String url = "";
        int cameraServiceIndex = 0;
        List<InstanceInfo> instances = eurekaClient.getApplication(appName).getInstances();
        for (InstanceInfo cameraServiceInstance : instances) {
            try {
                url = eurekaClient.getApplication(appName).getInstances().get(cameraServiceIndex).getHomePageUrl();
            } catch (NullPointerException e) {
                cameraServiceIndex++;
            }
        }
        return url;
    }
}
