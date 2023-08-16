package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.SignUpReq;
import com.app.dto.SignUpResp;
import com.app.entities.Employee;
import com.app.service.EmployeeService;
import com.app.service.ImageHandlingService;

@RestController // =@Controller at cls level + @ResponseBody added over ret
//types of all request  handling methods : @RequestMethod /@GetMapping/@PostMapping...
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	// dep : service layer i/f
	@Autowired
	private EmployeeService empService;
	@Autowired
	private ImageHandlingService imageservice;

	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}

	// http://host:port/employees , method=GET
	// add req handling method(API end point) to serve list of all emps
	@GetMapping
	public List<Employee> listAllEmps() {
		System.out.println("in list emps");
		return empService.getAllEmps();
	}

	// http://host:port/employees , method=POST
	// add req handling method(API end point) to add new emp
	@PostMapping
	// @RequestBody => method arg level annotation , for un marshalling=de ser (json
	// --> java)
	public Employee addNewEmp(@RequestBody Employee emp) {
		System.out.println("in add new emp " + emp);
		return empService.addEmpDetails(emp);
	}

	// http://host:port/employees/1234 , method=GET
	// add req handling method(API end point) to get emp details
	@GetMapping("/{empId}")
	// @PathVariable => method arg level annotation , to bind incoming URI variable
	// to the method argument
	public Employee getEmpDetails(@PathVariable Long empId) {
		System.out.println("in get emp details " + empId);
		return empService.getEmpDetails(empId);
	}

	// http://host:port/employees , method=PUT
	// add req handling method(API end point) to update emp details
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee emp) {
		System.out.println("in update emp " + emp.getId());// not null , existing in db
		return empService.updateEmpDetails(emp);
	}

	// http://host:port/employees/1234 , method=DELETE
	// add req handling method(API end point) to delete emp details
	@DeleteMapping("/{empId}")
//@PathVariable => method arg level annotation , to bind incoming URI variable
//to the method argument
	public ApiResponse deleteEmpDetails(@PathVariable Long empId) {
		System.out.println("in delete emp details " + empId);
		return new ApiResponse(empService.deleteEmpDetails(empId));
	}

	// http://host:port/employees/signin method=POST
	// req payload : Auth req dto'
	// resp : success --> AuthResp dto , SC 200
	// err : api resp : err mesg , SC 404
	// add req handling method(API end point) to authenticate emp
	@PostMapping("/signin")
	public ResponseEntity<?> signInEmployee(@RequestBody @Valid AuthRequest request) {
		System.out.println("auth req " + request);
		//try {
			AuthResp resp = empService.singInEmployee(request);
			return ResponseEntity.ok(resp);
//		} catch (RuntimeException e) {
//			System.out.println(e);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
//		}
  //
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpEmployee(@RequestBody @Valid SignUpReq request) {
		System.out.println("auth req " + request);
		try {
		SignUpResp resp = empService.singUpEmployee(request);
		return ResponseEntity.ok(resp);
		} catch (RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
}
	
	@PostMapping(value="/{empid}/image", consumes="multipart/form-data" )
	public ResponseEntity<?> uploadEmpImage(@PathVariable Long empid,@RequestParam MultipartFile imageFile) throws IOException{
		System.out.println("in image upload");
		return  ResponseEntity.status(HttpStatus.CREATED).body(imageservice.uploadImage(empid, imageFile));
	}
	
}
