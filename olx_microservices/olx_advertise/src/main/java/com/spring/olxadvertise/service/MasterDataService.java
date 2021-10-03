package com.spring.olxadvertise.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.spring.olxadvertise.dto.Advertise;


public interface MasterDataService {


		Advertise createAdvertise(Advertise advertise, String authToken);
		
		List<Advertise> getAllAdvertiseDetailsByUserName(String userName);

		Advertise getAdvertiseDetailsById(int Id, String authToken);

		Advertise updateAdvertiseById(Advertise advertise, int Id, String authToken);

		ResponseEntity<?> filterAdvertise(int page, int size, String title, String category, String status, Double price,
		String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, String order);

		List<Advertise> getAllAdvertiseByLoggedInUser(String authToken);

		Boolean deleteUsersAdvertiseById(String authToken, int Id);

		List<Advertise> searchAdvertisement(String searchtext);

	 
	}
