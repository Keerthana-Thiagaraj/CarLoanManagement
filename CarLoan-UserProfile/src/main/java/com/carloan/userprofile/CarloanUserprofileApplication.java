package com.carloan.userprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarloanUserprofileApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarloanUserprofileApplication.class, args);
	}

}
