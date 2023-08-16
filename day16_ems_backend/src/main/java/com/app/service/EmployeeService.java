package com.app.service;

import java.util.List;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.SignUpReq;
import com.app.dto.SignUpResp;
import com.app.entities.Employee;

public interface EmployeeService {
//add a method to get list of all emps
	List<Employee> getAllEmps();

	// add a method to add new emp details
	Employee addEmpDetails(Employee emp);// i/p : transient emp , id=null
	// add a method to get emp details by id

	Employee getEmpDetails(Long empId);

	// add a method to update existing emp details
	Employee updateEmpDetails(Employee emp);// detached emp , id=non null ,existing in db
	
	//add a method to delete existing emp details
	String deleteEmpDetails(Long empId);
	
	//add a method for signin
	AuthResp singInEmployee(AuthRequest request);
	   
	SignUpResp singUpEmployee(SignUpReq request);
}
