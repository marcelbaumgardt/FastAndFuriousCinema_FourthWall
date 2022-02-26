package com.marcel.baumgardt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FastAndFuriousCinemaFouthWallApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(FastAndFuriousCinemaFouthWallApplication.class)
				.build()
				.run(args);
	}
}