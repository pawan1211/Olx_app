package com.spring.olxadvertise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.olxadvertise.dto.Advertise;
import com.spring.olxadvertise.dto.AdvertiseList;
import com.spring.olxadvertise.dto.AdvertiseResponse;
import com.spring.olxadvertise.entity.AdvertiseEntity;
import com.spring.olxadvertise.exception.InvalidAdvertiseIdException;
import com.spring.olxadvertise.exception.InvalidAuthorizationTokenException;
import com.spring.olxadvertise.exception.InvalidfromDateSearchException;
import com.spring.olxadvertise.exception.InvalidonDateSearchException;
import com.spring.olxadvertise.exception.InvalidtoDateSearchException;
import com.spring.olxadvertise.repo.AdvertiseRepository;


@Service
public class MaterDataServiceImpl implements MasterDataService {

	@Autowired
	AdvertiseRepository advertiseRepo;

	@Autowired
	UserServiceDelegate userServiceDelegate;
	
	@Autowired
	CategoryServiceDelegate categoryServiceDelegate;

	@Override
	public Advertise createAdvertise(Advertise adv, String authToken) {

		
		if(userServiceDelegate.isLoggedInUser(authToken)) 
		{
		AdvertiseEntity adEntity = new AdvertiseEntity(adv.getTitle(), adv.getPrice(), adv.getCategory(),
				adv.getDescription(), adv.getUserName(), adv.getCreatedDate(), adv.getModifiedDate(), adv.getStatus());
		adEntity = advertiseRepo.save(adEntity);
		adv = new Advertise(adEntity.getId(), adEntity.getTitle(), adEntity.getPrice(), adEntity.getCategory(),
				adEntity.getDescription(), adEntity.getUserName(), adEntity.getCreatedDate(),
				adEntity.getModifiedDate(), adEntity.getStatus());
		return adv;
		}
		throw new InvalidAuthorizationTokenException(authToken);	
	}
	
	@Override
	public List<Advertise> getAllAdvertiseDetailsByUserName(String userName) {

		List<AdvertiseEntity> advertiseEntitiesList = advertiseRepo.findByUserName(userName);
		List<Advertise> advertiseList = new ArrayList();
		for (AdvertiseEntity advertiseEntity : advertiseEntitiesList) {
			Advertise advertise = new Advertise(advertiseEntity.getId(), advertiseEntity.getTitle(),
					advertiseEntity.getPrice(), advertiseEntity.getCategory(), advertiseEntity.getDescription(),
					advertiseEntity.getUserName(), advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(),
					advertiseEntity.getStatus());
			advertiseList.add(advertise);
		}
		return advertiseList;

	}


	@Override
	public Advertise getAdvertiseDetailsById(int Id, String authToken) {
	
		if(userServiceDelegate.isLoggedInUser(authToken)) 
		{
		Optional<AdvertiseEntity> opstockEntity = advertiseRepo.findById(Id);
		if (opstockEntity.isPresent()) {
			AdvertiseEntity advertiseEntity = opstockEntity.get();
			Advertise advertise = new Advertise(advertiseEntity.getId(), advertiseEntity.getTitle(),
					advertiseEntity.getPrice(), advertiseEntity.getCategory(), advertiseEntity.getDescription(),
					advertiseEntity.getUserName(), advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(),
					advertiseEntity.getStatus());
			return advertise;
		}
		throw new InvalidAdvertiseIdException("Custome Exception:" + Id);
		}
		throw new InvalidAuthorizationTokenException(authToken);
	}


	@Override
	public Advertise updateAdvertiseById(Advertise advertise, int Id, String authToken) {
	
		if(userServiceDelegate.isLoggedInUser(authToken)) 
		{
		Optional<AdvertiseEntity> opstockEntity = advertiseRepo.findById(Id);
		if (opstockEntity.isPresent()) {
			AdvertiseEntity advertiseEntity = opstockEntity.get();
			advertiseEntity.setTitle(advertise.getTitle());
			advertiseEntity.setPrice(advertise.getPrice());
			advertiseEntity.setCategory(advertise.getCategory());
			advertiseEntity.setDescription(advertise.getDescription());
			advertiseEntity.setUserName(advertise.getUserName());
			advertiseEntity.setCreatedDate(advertise.getCreatedDate());
			advertiseEntity.setModifiedDate(advertise.getModifiedDate());
			advertiseEntity.setStatus(advertise.getStatus());
			advertiseRepo.save(advertiseEntity);
			advertise.setId(Id);
			return advertise;
		}
		throw new InvalidAdvertiseIdException("Custome Exception:" + Id);
		}
		throw new InvalidAuthorizationTokenException(authToken);
	}
	
	@Override
	public ResponseEntity<?> filterAdvertise(int page, int size, String title, String category, String status,
			Double price, String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy,
			String order) {
		
		 if (dateCondition.equals("EQUAL")) {
	            if (onDate == null)
	                throw new InvalidonDateSearchException(" inserting null value for onDate");
	        }
	 
        if (dateCondition.equals("LESS THAN")) {
            if (toDate == null)
                throw new InvalidtoDateSearchException(" inserting null value for toDate");
        }
        if (dateCondition.equals("GREATER THAN")) {
            if (fromDate == null)
                throw new InvalidfromDateSearchException("inserting null value for FromDate");
        }
       
        
 		if (dateCondition.equals("BETWEEN")) {
             if (fromDate == null)
                 throw new InvalidfromDateSearchException(" inserting null value for FromDate");
             if (toDate == null)
                 throw new InvalidtoDateSearchException(" inserting null value for toDate");
         }
	        List<AdvertiseEntity> advertiseList = advertiseRepo.filterAdvertise(page,size,title,category,status,price,dateCondition,onDate,fromDate,toDate);         
	        if (sortBy !=null)
	            advertiseList = sort(advertiseList,sortBy);
	        if (order !=null && order.equals("des"))
	            Collections.reverse(advertiseList);
	        
	        List<AdvertiseResponse> advertiseResponseList = new ArrayList<>();
	        settle(advertiseList, advertiseResponseList);
	        return ResponseEntity.ok(new AdvertiseList(advertiseResponseList));
	        
	}

	@Override
	public List<Advertise> getAllAdvertiseByLoggedInUser(String authToken) {
		
		if(userServiceDelegate.isLoggedInUser(authToken)) 
		{
			List<AdvertiseEntity> advertiseEntityList = advertiseRepo.findAll();
			List<Advertise> advertiseList = new ArrayList();
			for (AdvertiseEntity advertiseEntity : advertiseEntityList) {
				Advertise advertise = new Advertise(advertiseEntity.getId(), advertiseEntity.getTitle(),
						advertiseEntity.getPrice(), advertiseEntity.getCategory(), advertiseEntity.getDescription(),
						advertiseEntity.getUserName(), advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(),
						advertiseEntity.getStatus());
				advertiseList.add(advertise);
			}
			return advertiseList;
		}
		throw new InvalidAuthorizationTokenException(authToken);
	}

	@Override
	public Boolean deleteUsersAdvertiseById(String authToken, int Id) {

	
		if(userServiceDelegate.isLoggedInUser(authToken)) 
		{
			if (advertiseRepo.existsById(Id)) {
				advertiseRepo.deleteById(Id);
				return true;
			}
			throw new InvalidAdvertiseIdException("Custome Exception" + Id);
		}
		throw new InvalidAuthorizationTokenException(authToken);
	}

	@Override
	public List<Advertise> searchAdvertisement(String searchtext) {
		if(searchtext!=null)
		{
			List<AdvertiseEntity> advertiseEntityList = advertiseRepo.search(searchtext);
			
			List<Advertise> advertiseList = new ArrayList();
			for (AdvertiseEntity advertiseEntity : advertiseEntityList) {
				Advertise advertise = new Advertise(advertiseEntity.getId(), advertiseEntity.getTitle(),
						advertiseEntity.getPrice(), advertiseEntity.getCategory(), advertiseEntity.getDescription(),
						advertiseEntity.getUserName(), advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(),
						advertiseEntity.getStatus());
				advertiseList.add(advertise);
			}
			return advertiseList;
		}
		return null;
		
	}



	private List<AdvertiseEntity> sort(List<AdvertiseEntity> advertiseList, String sortBy) {
		switch (sortBy){
		case "status" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getStatus)).collect(Collectors.toList());
			break;
		case "category" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getCategory)).collect(Collectors.toList());
			break;
	
		case "price" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingDouble(AdvertiseEntity::getPrice)).collect(Collectors.toList());
			break;	
		case "title" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getTitle)).collect(Collectors.toList());
			break;
		case "createddate" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getCreatedDate)).collect(Collectors.toList());
			break;
		default:
	}
	return advertiseList;
	}

	private void settle(List<AdvertiseEntity> advertiseList, List<AdvertiseResponse> advertiseResponseList) {

		for (AdvertiseEntity adve : advertiseList)
		{
			AdvertiseResponse adv = new AdvertiseResponse(adve.getId(), adve.getPrice(), adve.getTitle(),
					adve.getDescription(),  adve.getCategory(),adve.getStatus(), adve.getModifiedDate(),
					adve.getCreatedDate());
			advertiseResponseList.add(adv);
		}
		
	}
	

}
