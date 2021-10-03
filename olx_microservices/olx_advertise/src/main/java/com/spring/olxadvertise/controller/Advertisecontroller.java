package com.spring.olxadvertise.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.olxadvertise.dto.Advertise;
import com.spring.olxadvertise.exception.InvalidAdvertiseIdException;
import com.spring.olxadvertise.exception.InvalidAuthorizationTokenException;
import com.spring.olxadvertise.exception.InvalidCategoryException;
import com.spring.olxadvertise.exception.InvalidStatusException;
import com.spring.olxadvertise.service.MasterDataService;


@RestController
public class Advertisecontroller {
	
	@Autowired
	MasterDataService masterDataService;
	
	@ExceptionHandler(InvalidAdvertiseIdException.class)
	public ResponseEntity<String> handleInvalidCategoryIdException(InvalidAdvertiseIdException invalidAdvertiseIdException) {
		return new ResponseEntity<String>(invalidAdvertiseIdException.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidAuthorizationTokenException.class)
	public ResponseEntity<String> handleInvalidAuthorizationException(InvalidAuthorizationTokenException invalidAuthorizationTokenException) {
		return new ResponseEntity<String>(invalidAuthorizationTokenException.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCategoryException.class)
	public ResponseEntity<String> handleInvalidCategoryException(InvalidCategoryException invalidCategoryException) {
		return new ResponseEntity<String>(invalidCategoryException.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidStatusException.class)
	public ResponseEntity<String> handleInvalidStatusException(InvalidStatusException invalidStatusException) {
		return new ResponseEntity<String>(invalidStatusException.toString(), HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value="/advertise",consumes= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertise>createAdvertise(@RequestBody Advertise adv,@RequestHeader("Authorization")String authToken)
	{
		
		Advertise advertise = masterDataService.createAdvertise(adv,authToken);
		return new ResponseEntity(advertise,HttpStatus.OK);
	}
	
	
	@PutMapping(value="/advertise/{id}",consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Advertise>updateAdvertiseById(@RequestBody Advertise advertise,@PathVariable ("id")int Id,@RequestHeader("Authorization")String authToken)
	{
		
		return new ResponseEntity<Advertise>(masterDataService.updateAdvertiseById( advertise,Id,authToken),HttpStatus.OK);
	}
	
	
	@GetMapping(value="/user/advertise",produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public List<Advertise>getALlAdvertiseByLoggedInUser(@RequestHeader("Authorization")String authToken)
	{
		return masterDataService.getAllAdvertiseByLoggedInUser(authToken);
	
	}
	

	@GetMapping(value="/user/advertise/{id}",produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public Advertise getUsersAdvertiseById(@RequestHeader("Authorization")String authToken,@PathVariable("id")int Id)
	{
		return masterDataService.getAdvertiseDetailsById(Id,authToken);
	
	}
	

	@DeleteMapping(value="/user/advertise/{id}",produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean>deleteUsersAdvertiseById(@RequestHeader("Authorization")String authToken,@PathVariable("id")int Id)
	{
	
		return new ResponseEntity<Boolean>(masterDataService.deleteUsersAdvertiseById(authToken,Id),HttpStatus.OK);
	}
	

	
	@GetMapping(value = "advertise/search/filtercriteria" ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	

	public ResponseEntity<?> filterAdvertise(@RequestParam(required = false) String title,
            @RequestParam(required = false) String cate,
            @RequestParam(required = false) String sta,
            @RequestParam(required = false) Double pri,
            @RequestParam(required = false) String dateCondi,
            @RequestParam(required = false) @DateTimeFormat(iso =ISO.DATE) LocalDate onDate,
            @RequestParam(required = false) @DateTimeFormat(iso =ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso =ISO.DATE) LocalDate toDate,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size) {
		
   return masterDataService.filterAdvertise(page,size,title,cate,sta,pri,dateCondi,onDate,fromDate,toDate,sortBy,order);
}
	
	
	@GetMapping(value="/advertise/{id}",produces = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE})
	public Advertise getAdvertiseDetailsById(@PathVariable("id") int Id,@RequestHeader("Authorization")String authToken)
	{	
		return masterDataService.getAdvertiseDetailsById(Id,authToken);
	}
		
	
	@GetMapping(value="/advertise/search", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Advertise>> searchAdvertisement(@RequestParam("searchtext") String searchtext) {
		
		List<Advertise> adv = masterDataService.searchAdvertisement(searchtext);
		
		if(adv.isEmpty()) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(searchtext == null) {
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(adv, HttpStatus.OK);
	}
	
	

	
	
	

}
