package com.marzag.cameraserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CameraServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CameraServerApplication.class, args);
	}

}
