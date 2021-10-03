package com.spring.olxadvertise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceDelecateImple implements UserServiceDelegate{

	
	
	@Autowired
	RestTemplate restTemplate;
	

	@Override
	public boolean isLoggedInUser(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",authToken);
	    HttpEntity entity = new HttpEntity(headers);
	    try {
	    ResponseEntity<Boolean> result = this.restTemplate.exchange("http://auth-service/token/validate", HttpMethod.GET, entity, Boolean.class);
	    if(result.getStatusCode() == HttpStatus.OK)
			return result.getBody();
		else
			return false;
	    }
	    catch(Exception e) {
			return false;
		}
	    
	}

}
