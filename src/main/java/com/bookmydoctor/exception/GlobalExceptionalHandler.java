package com.bookmydoctor.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.bookmydoctor.response.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionalHandler {
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseDto userAlreadyExists(UserAlreadyExist userAlreadyExist) {
		return new ResponseDto(false, userAlreadyExist.getMessage(), null);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDto methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
	    Map<String,String> map = new HashMap<>();
	    for(FieldError fieldError:methodArgumentNotValidException.getBindingResult().getFieldErrors())
	    {
	    	map.put(fieldError.getField(),fieldError.getDefaultMessage());
	    }
		return new ResponseDto(false, "please pass the valid arguments", map);
	}
	@ExceptionHandler(UserNotExist.class)
	public ResponseDto userNotExists(UserNotExist userNotExist) {
		return new ResponseDto(false, userNotExist.getMessage(), null);
	}
	@ExceptionHandler(PasswordMismatch.class)
	public ResponseDto passwordMismatch(PasswordMismatch passwordMismatch) {
		return new ResponseDto(false, passwordMismatch.getMessage(), null);
	}
	@ExceptionHandler(DoctorAlreadyExists.class)
	public ResponseDto doctorAlreadyExists(DoctorAlreadyExists passwordMismatch) {
		return new ResponseDto(false, passwordMismatch.getMessage(), null);
	}
	@ExceptionHandler(NoDoctorsAvailable.class)
	public ResponseDto passwordMismatch(NoDoctorsAvailable passwordMismatch) {
		return new ResponseDto(false, passwordMismatch.getMessage(), null);
	}
	@ExceptionHandler(NoAppointmentsFound.class)
	public ResponseDto noAppointmentsFound(NoAppointmentsFound passwordMismatch) {
		return new ResponseDto(false, passwordMismatch.getMessage(), null);
	}
	@ExceptionHandler(InternalServerError.class)
	public ResponseDto noAppointmentsFound(InternalServerError passwordMismatch) {
		return new ResponseDto(false, passwordMismatch.getMessage(), null);
	}
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseDto noAppointmentsFound(java.sql.SQLIntegrityConstraintViolationException passwordMismatch) {
		return new ResponseDto(false, passwordMismatch.getMessage(), null);
	}
}
