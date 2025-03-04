 package com.example.barseek_rating_mservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

 @SpringBootApplication
@EnableDiscoveryClient
class BarseekRatingMserviceApplication

fun main(args: Array<String>) {
	runApplication<BarseekRatingMserviceApplication>(*args)
}
