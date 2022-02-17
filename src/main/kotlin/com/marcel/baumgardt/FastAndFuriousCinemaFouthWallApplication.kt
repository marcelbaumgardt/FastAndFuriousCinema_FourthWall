package com.marcel.baumgardt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class FastAndFuriousCinemaFouthWallApplication

fun main(args: Array<String>) {
	runApplication<FastAndFuriousCinemaFouthWallApplication>(*args)
}
