package com.bookmydoctor.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmydoctor.repository.DoctorRepository;
import com.bookmydoctor.repository.LeaveRepository;
import com.bookmydoctor.repository.UserRepository;
import com.bookmydoctor.request.LoginRequestDto;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private UserRepository userRepository ;
	
	@Override
	public Object loginRequest(LoginRequestDto loginRequestDto) {
		if(doctorRepository.findByDoctorEmail(loginRequestDto.getEmail()) != null)
			return "doctorsuccessfully logined";
		else if(userRepository.findByUserEmail(loginRequestDto.getEmail()) != null)
			return "user successfully logined";
		return null;
	}



}
