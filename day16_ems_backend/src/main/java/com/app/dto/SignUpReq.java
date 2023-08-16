package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpReq {

	@Length(min = 4, max = 20, message = "invaid email length ")
	private String firstName;

	private String lastName;
  @Email
	private String email;
	private String password;
	@Future(message = "join date must be in futute")
	private LocalDate joinDate;
	@Range(min = 50000, max = 150000)
	private double salary;

	private String location;
	@NotBlank(message = "deparment not blank")
	private String department;
}
