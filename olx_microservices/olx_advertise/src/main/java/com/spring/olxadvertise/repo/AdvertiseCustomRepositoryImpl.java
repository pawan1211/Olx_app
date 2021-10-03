package com.spring.olxadvertise.repo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.olxadvertise.entity.AdvertiseEntity;

@Repository
@Transactional

public class AdvertiseCustomRepositoryImpl implements AdvertiseCustomRepository{

	@Autowired
    EntityManager entityManager;

	@Override
	public List<AdvertiseEntity> filterAdvertise(int page, int size, String title, String category, String status,
			Double price, String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate) {
		StringBuilder statusQuery = new StringBuilder();

        statusQuery.append("");
        
        if (title != null){
            statusQuery.append("advertise title "+title+"' ");
        }
        if (category != null){
            statusQuery.append("advertise category "+category+"");
        }
        if (status != null){
            statusQuery.append("advertise status  "+status+"");
        }
        if (price != null){
            statusQuery.append("advertise price "+price+"");
        }
		 if (dateCondition !=null && dateCondition.equalsIgnoreCase("LESS THAN")  && toDate !=null){
            statusQuery.append("advertise createdDate "+toDate+"' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("GREATER THAN")  && fromDate !=null){
            statusQuery.append("advertise createdDate '"+fromDate+"' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("EQUAL")  && onDate !=null){
            statusQuery.append("advertise createdDate BETWEEN '"+onDate+"' AND '"+onDate+"' ");
        }
        if (dateCondition !=null && dateCondition.equalsIgnoreCase("BETWEEN") && fromDate !=null && toDate !=null){
            statusQuery.append("advertise.createdDate BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
        }
     
    
        

        javax.persistence.Query query = entityManager.createQuery(" SELECT advertise FROM AdvertiseEntity "+
                statusQuery.toString(),AdvertiseEntity.class);

        query.setFirstResult((page) * size);
        query.setMaxResults(size);

        return  query.getResultList();
	}

}
