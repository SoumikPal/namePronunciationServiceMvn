package com.hackathon.paymenttracker.namepronunciation.controller;


import java.util.ArrayList;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.paymenttracker.namepronunciation.repository.EmployeeNameDBRepository;
import com.azure.cosmos.models.PartitionKey;
import com.hackathon.paymenttracker.namepronunciation.model.EmployeeCrudResponse;
import com.hackathon.paymenttracker.namepronunciation.model.EmployeeName;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/employeeNames")
public class EmployeePronunciationController  {

    @Autowired
    EmployeeNameDBRepository employeeNameDBRepository;


    Logger logger = LoggerFactory.getLogger(EmployeePronunciationController.class);

    //Add new employeeName
    @PostMapping
    public ResponseEntity<EmployeeCrudResponse> createNewemployeeName(@RequestBody EmployeeName c) {

        c = employeeNameDBRepository.save(c);
        EmployeeCrudResponse employeeCrudResponse = new EmployeeCrudResponse();
        employeeCrudResponse.setMessage("New Employee Details created successfully with the ID: " + c.getId());
        employeeCrudResponse.setStatusCode("00");
        return new ResponseEntity<EmployeeCrudResponse>(employeeCrudResponse, HttpStatus.CREATED);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<String> updateExistingemployeeName(@PathVariable String id, @RequestBody EmployeeName c) {

    	employeeNameDBRepository.save(c);
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }


    //get employeeName details
    @GetMapping("/{id}")
    public ResponseEntity<List<EmployeeName>> getEmployeeName(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("ContentType", "application/json");
        List<EmployeeName> employeeNameList = new ArrayList<>();

        logger.info("Id is present in the GET request");
        List<Optional<EmployeeName>> optionaemployeeNameList = Collections.singletonList(employeeNameDBRepository.findById(id));
        if (!(optionaemployeeNameList.get(0).isEmpty())) {
            optionaemployeeNameList.stream().forEach(c -> c.ifPresent(employeeName -> employeeNameList.add(employeeName)));
            return new ResponseEntity<List<EmployeeName>>(employeeNameList, responseHeaders, HttpStatus.OK);
        }


        return new ResponseEntity<List<EmployeeName>>(employeeNameList, responseHeaders, HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity<List<EmployeeName>> getAllEmployeeName() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("ContentType", "application/json");
        List<EmployeeName> employeeNameList = new ArrayList<>();

        logger.info("Id is not present in the GET request");
        employeeNameList = employeeNameDBRepository.getAllEmployeeNames();
        return new ResponseEntity<List<EmployeeName>>(employeeNameList, responseHeaders, HttpStatus.OK);

    }

    //delete the employeeName of a particular id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExistingemployeeName(@PathVariable String id) {
        Optional<EmployeeName> employeeName = employeeNameDBRepository.findById(id);
        employeeNameDBRepository.deleteById(id, new PartitionKey(employeeName.get().getName()));
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }
}
