package com.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OlxApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxApiGatewayApplication.class, args);
	}

}
