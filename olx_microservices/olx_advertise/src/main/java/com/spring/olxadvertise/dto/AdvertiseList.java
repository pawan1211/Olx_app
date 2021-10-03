package com.spring.olxadvertise.dto;

import java.util.List;



public class AdvertiseList {

	private List<AdvertiseResponse> advertises;

	public AdvertiseList()
	{
		
	}

	public AdvertiseList(List<AdvertiseResponse> advertises) {
		super();
		this.advertises = advertises;
	}

	public List<AdvertiseResponse> getAdvertises() {
		return advertises;
	}

	public void setAdvertises(List<AdvertiseResponse> advertises) {
		this.advertises = advertises;
	}

	@Override
	public String toString() {
		return "AdvertiseListDto [advertises=" + advertises + "]";
	}
	
}
