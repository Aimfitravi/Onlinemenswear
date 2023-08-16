package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.EmployeeDao;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.SignUpReq;
import com.app.dto.SignUpResp;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	// dep : dao layer i/f
	
	//two dependanacy
	//1
	@Autowired
	private EmployeeDao empDao;
   //2
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Employee> getAllEmps() {
		return empDao.findAll();
	}

	@Override
	public Employee addEmpDetails(Employee emp) {
		System.out.println("emp " + emp);// id : null
		return empDao.save(emp);
	}// insert

	@Override
	public Employee getEmpDetails(Long empId) {
		return empDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID !!!!!"));
	}

	@Override
	public Employee updateEmpDetails(Employee emp) {
		Employee foundEmp = empDao.findById(emp.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID !!!!!"));
		// emp exists !, foundEmp : persistent
		return empDao.save(emp);
	}// detached emp

	@Override
	public String deleteEmpDetails(Long empId) {
		Employee foundEmp = empDao.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID !!!!!"));
		// emp exists !, foundEmp : persistent
		empDao.deleteById(empId);
		return "Emp details deleted successfully!";
	}

	@Override
	public AuthResp singInEmployee(AuthRequest request) {
		// invoke dao layer method for authentication
		Employee emp = empDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Ravi    Invalid Email or Password !!!!"));
		// => signin success , emp : persistent
		// ModelMapper API
		// public DestType map(Object src ,Class<DestType> cls)
		return mapper.map(emp, AuthResp.class);
	}

	@Override
	public SignUpResp singUpEmployee(SignUpReq req) {
		
		Employee emp= empDao.save(mapper.map(req, Employee.class));
		return mapper.map(emp, SignUpResp.class);
	}
	
}
