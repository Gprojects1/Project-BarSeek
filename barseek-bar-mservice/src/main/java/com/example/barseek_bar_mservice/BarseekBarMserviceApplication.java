package com.example.barseek_bar_mservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarseekBarMserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarseekBarMserviceApplication.class, args);
	}

}

// добавить логику отправки в кафку в сервисах
// добавить валидацию запросов . если роль не бар , то не давать ничего изменять или добавлять .
