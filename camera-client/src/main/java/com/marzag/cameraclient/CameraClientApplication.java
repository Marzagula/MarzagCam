package com.marzag.cameraclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CameraClientApplication {

	public static final Logger log = LoggerFactory.getLogger(CameraClientApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CameraClientApplication.class, args);
	}

}
