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

// добавить логику отправки в кафку в сервисах
// добавить валидацию запросов т е авторизацию в сервисах
// добавить auth сервис
// security config
// ОШИБКА в строке 28 BarController, видимо надо передавать корректный Jwt токен с ролью и айди , при этом там ошибка не NumberFormat , а просто Exeption почему то
// 36 , 44 ошибки скорее всего тк одинаковые пути , при этом 75 работает
// айди юзера оттдельно
// убрать фильтры jwt из сервисов , оставить в api-gateway , в сервисах данные через @RequestHeader, создать новую ветку с этой реализацией, добавить фильтры которые проверяют не пуст ли заголовок userId, добавить eureka и api-gateway через spring cloud