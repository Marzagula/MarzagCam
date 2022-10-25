package com.marzag.raspberrymanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class RaspberryManagerApplication {
	static final Logger log = LoggerFactory.getLogger(RaspberryManagerApplication.class);

	public static void main(String[] args)  {

		SpringApplication.run(RaspberryManagerApplication.class, args);
		log.debug("Starting my application in debug with {} args", args.length);
		log.info("Starting my application with {} args.", args.length);
	}

}
