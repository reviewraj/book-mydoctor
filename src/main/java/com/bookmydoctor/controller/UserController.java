package com.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmydoctor.request.UserRequestDto;
import com.bookmydoctor.response.ResponseDto;
import com.bookmydoctor.service.UserService;

@RestController
@RequestMapping("/bookmydoctor/api/user")
public class UserController {
	@Autowired
	private UserService  userService;
	@PostMapping("/save")
	public ResponseDto saveUser(@RequestBody UserRequestDto userRequestDto ) {
		return new ResponseDto(false,"user created successfully",userService.save(userRequestDto));
	}
	@PutMapping("/update")
	public ResponseDto updateUser(@RequestBody UserRequestDto userRequestDto ) {
		return new ResponseDto(false,"user updated successfully",userService.update(userRequestDto));
	}
	@DeleteMapping("/delete")
	public ResponseDto deleteUser(@RequestBody UserRequestDto userRequestDto ) {
		return new ResponseDto(false,"user deleted successfully",userService.delete(userRequestDto));
	}


}
