package com.example.barseek_bar_mservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BarseekBarMserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarseekBarMserviceApplication.class, args);
	}

}


