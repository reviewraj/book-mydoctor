package com.bookmydoctor.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookmydoctor.entity.User;
import com.bookmydoctor.enums.Status;
import com.bookmydoctor.exception.PasswordMismatch;
import com.bookmydoctor.exception.UserAlreadyExist;
import com.bookmydoctor.exception.UserNotExist;
import com.bookmydoctor.repository.UserRepository;
import com.bookmydoctor.request.UserRequestDto;
import com.bookmydoctor.response.UserResponseDto;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserResponseDto save(UserRequestDto userRequestDto) {
		Optional<User> optional = userRepository.findByUserEmail(userRequestDto.getUserEmail());
		if (optional.isPresent())
			throw new UserAlreadyExist("user already exits with this email : " + userRequestDto.getUserEmail());
		User userEntity = new User();
		modelMapper.map(userRequestDto, userEntity);
		User dbUser = userRepository.save(userEntity);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		return userResponseDto;

	}

	@Override
	public UserResponseDto update(UserRequestDto userRequestDto) {
		Optional<User> optional = userRepository.findByUserEmail(userRequestDto.getUserEmail());
		if (optional.isEmpty())
			throw new UserNotExist("user not exits with this email : " + userRequestDto.getUserEmail()
					+ " please create the user first");

		else if (!optional.get().getPassword().equals(userRequestDto.getPassword())) {
			throw new PasswordMismatch("please enter associated password with : " + userRequestDto.getUserEmail());
		}
		User userEntity = optional.get();
		modelMapper.map(userRequestDto, userEntity);
		User dbUser = userRepository.save(userEntity);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		return userResponseDto;

	}

	@Override
	public UserResponseDto delete(UserRequestDto userRequestDto) {
		Optional<User> optional = userRepository.findByUserEmail(userRequestDto.getUserEmail());
		if (optional.isEmpty())
			throw new UserNotExist("user not exits with this email : " + userRequestDto.getUserEmail());

		else if (!optional.get().getPassword().equals(userRequestDto.getPassword())) {
			throw new PasswordMismatch("please enter associated password with : " + userRequestDto.getUserEmail());
		}
		User userEntity = optional.get();
		if(userEntity.getStatus()==Status.INACTIVE)throw new UserNotExist("user not exits with this email : " + userRequestDto.getUserEmail());
		userEntity.setStatus(Status.INACTIVE);
		User dbUser = userRepository.save(userEntity);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		return userResponseDto;

	}

}
