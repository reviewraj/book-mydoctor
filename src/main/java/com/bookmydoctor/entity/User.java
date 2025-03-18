package com.bookmydoctor.entity;

import com.bookmydoctor.enums.Gender;
import com.bookmydoctor.enums.Role;
import com.bookmydoctor.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Integer userId;

	@Column(name = "username",nullable = false)
	private String userName;

	@Column(name = "useremail", nullable = false, unique = true)
	private String userEmail;

	@Column(name = "usernumber", nullable = false, unique = true)
	private String phoneNumber;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role=Role.USER;

}
