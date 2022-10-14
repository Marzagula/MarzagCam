package com.marzag.raspberrymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class RaspberryManagerApplication {


	public static void main(String[] args)  {
		SpringApplication.run(RaspberryManagerApplication.class, args);
	}

}
