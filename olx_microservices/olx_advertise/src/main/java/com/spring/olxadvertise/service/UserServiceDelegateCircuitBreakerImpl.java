package com.spring.olxadvertise.service;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.net.HttpHeaders;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;


@Service
public class UserServiceDelegateCircuitBreakerImpl implements UserServiceDelegate{

	

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public boolean isLoggedInUser(String authToken) {
		CircuitBreaker  circuitBreaker = circuitBreakerFactory.create("AUTH_TOKEN_VALIDATION");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",authToken);
	    HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> result = circuitBreaker.run(()->this.restTemplate.exchange("http://auth-service/token/validate", HttpMethod.GET, entity, Boolean.class),
				throwable-> fallbackForisLoggedInUser(authToken,throwable));
		return false;
	}
	
	public ResponseEntity<Boolean> fallbackForisLoggedInUser(String authToken,Throwable throwable)
	{
		System.out.println("Login service Failed: "+throwable);
		return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
