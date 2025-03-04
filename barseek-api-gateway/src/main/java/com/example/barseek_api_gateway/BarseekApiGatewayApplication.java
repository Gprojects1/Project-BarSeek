package com.example.barseek_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BarseekApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarseekApiGatewayApplication.class, args);
	}

}

// писать апи гейтвей , фильтр !
