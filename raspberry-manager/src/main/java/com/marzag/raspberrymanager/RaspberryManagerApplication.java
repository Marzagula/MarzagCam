package com.marzag.raspberrymanager;

import com.marzag.raspberrymanager.util.FileManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootApplication
@EnableEurekaClient
public class RaspberryManagerApplication {

	public static void main(String[] args)  { SpringApplication.run(RaspberryManagerApplication.class, args); }

}
