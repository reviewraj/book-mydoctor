package com.bookmydoctor.service;

import com.bookmydoctor.request.UserRequestDto;
import com.bookmydoctor.response.UserResponseDto;

public interface UserService {

	UserResponseDto save(UserRequestDto userRequestDto);

	UserResponseDto update(UserRequestDto userRequestDto);

	UserResponseDto delete(UserRequestDto userRequestDto);

	

}
