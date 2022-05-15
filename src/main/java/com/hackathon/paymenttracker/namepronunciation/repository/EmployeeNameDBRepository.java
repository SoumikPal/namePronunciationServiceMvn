package com.hackathon.paymenttracker.namepronunciation.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.hackathon.paymenttracker.namepronunciation.model.EmployeeName;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeNameDBRepository extends CosmosRepository<EmployeeName, String>{
	
	 @Query(value = "SELECT * FROM c")
	    List<EmployeeName> getAllEmployeeNames();
	

}
