package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
//add a finder method for emp's signin
	Optional<Employee> findByEmailAndPassword(String em,String pass);
}
