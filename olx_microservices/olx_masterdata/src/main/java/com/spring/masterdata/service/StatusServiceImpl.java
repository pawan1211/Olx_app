package com.spring.masterdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.masterdata.dto.Status;
import com.spring.masterdata.entity.StatusEntity;
import com.spring.masterdata.repo.StatusRepo;

@Service
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	StatusRepo statusRepo;
	
	@Override
	public List<Status> getAllStatus() {
		List<StatusEntity> statusEntityList = statusRepo.findAll();
		List<Status> statusList = new ArrayList(); 
		for(StatusEntity statusEntity:statusEntityList)
		{
			Status status = new Status(statusEntity.getId(),statusEntity.getStatus());
			statusList.add(status);
		}
		return statusList;
	}
}
