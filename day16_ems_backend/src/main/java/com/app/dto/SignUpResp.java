package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class SignUpResp {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate joinDate;
	private double salary;
}
