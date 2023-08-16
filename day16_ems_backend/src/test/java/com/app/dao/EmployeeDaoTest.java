package com.app.dao;



import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Employee;

@DataJpaTest //spring boot test annotation to enable ONLY DAO layer n entities
@AutoConfigureTestDatabase(replace = Replace.NONE) // DO NOT replace 
//main db by test db
@Rollback(false) //after completion of test cases DO NOT rollback tx.
class EmployeeDaoTest {
	@Autowired
	private EmployeeDao empDao;
      
	@Test
	void testAddEmps() {
		List<Employee> list = List.of(
				new Employee("a1", "b1", "a1@gmail.com", "1234", LocalDate.parse("2020-10-20"), 34567, "Pune", "RnD"),
				new Employee("a2", "b2", "a2@gmail.com", "2234", LocalDate.parse("2020-11-20"), 44567, "Pune", "RnD"),
				new Employee("a3", "b3", "a3@gmail.com", "1274", LocalDate.parse("2023-10-20"), 14567, "Pune", "RnD"),
				new Employee("a4", "b4", "a4@gmail.com", "2234", LocalDate.parse("2022-10-20"), 40567, "Mumbai","Finance"));
		empDao.saveAll(list);
		Assertions.assertTrue(empDao.count()==4);
	
	}

}
