package com.example.barseek_api_gateway;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BarseekApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarseekApiGatewayApplication.class, args);
	}

}

// писать апи гейтвей , фильтр !
// закончил при гейтвей , надо убирать двт фильтры и аутентиф из сервисов . так же отключить дефолтную аутентиф из сервисов или разрешить по всем эндпоинтам обращаться .
// в бар сервисе еще задачи