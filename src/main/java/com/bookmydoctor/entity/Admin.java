package com.bookmydoctor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adminid")
	private Integer adminId;
	
	@Column(name = "adminname",nullable = false)
	private String adminName;

	@Column(name = "adminemail", nullable = false, unique = true)
	private String adminEmail;
	
	@Column(nullable = false)
	private String password;
	

}
