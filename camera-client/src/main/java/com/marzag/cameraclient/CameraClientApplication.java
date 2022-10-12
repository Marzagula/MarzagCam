package com.marzag.cameraclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CameraClientApplication {

	public static void main(String[] args) {

		SpringApplication.run(CameraClientApplication.class, args);
	}

}
