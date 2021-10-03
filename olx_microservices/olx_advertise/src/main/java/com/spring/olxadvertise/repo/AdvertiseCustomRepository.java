package com.spring.olxadvertise.repo;

import java.time.LocalDate;
import java.util.List;

import com.spring.olxadvertise.entity.AdvertiseEntity;



public interface AdvertiseCustomRepository {
	
	List<AdvertiseEntity> filterAdvertise(int page, int size, String title, String category, String status,
Double price, String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate);


}
