package com.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmydoctor.request.LoginRequestDto;
import com.bookmydoctor.response.ResponseDto;
import com.bookmydoctor.service.LoginService;

@RestController
@RequestMapping("/bookmydoctor/api")
public class GenericController {
	@Autowired
	private LoginService loginService;
	@PostMapping("/login")
	public ResponseDto loginRequest(@RequestBody LoginRequestDto loginRequestDto ) {
		return new ResponseDto(false,"login is succesfully",loginService.loginRequest(loginRequestDto));
	}
}
