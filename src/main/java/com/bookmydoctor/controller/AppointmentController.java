package com.bookmydoctor.controller;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmydoctor.request.AppointmentRequestDto;
import com.bookmydoctor.response.ResponseDto;
import com.bookmydoctor.service.AppointmentService;

import jakarta.validation.Valid;
@Aspect
@RestController
@RequestMapping("/bookmydoctor/api/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService  appointmentService;
	
	@PostMapping("/book")
	public ResponseEntity<ResponseDto> bookAppointment(@RequestBody @Valid AppointmentRequestDto appointmentRequestDto ) {
		return ResponseEntity.ok( new ResponseDto(false,"Appointment has requested successfully",appointmentService.createAppointment(appointmentRequestDto)));
	}
	@GetMapping("/getByUserId")
	public ResponseEntity<ResponseDto> getByUserId(@RequestParam Integer UserId ) {
		return ResponseEntity.ok( new ResponseDto(false,"Appointment has listed successfully",appointmentService.getAppointmentsById(UserId)));
	}
	@GetMapping("/getByDocterId")
	public ResponseEntity<ResponseDto> getByDocterId(@RequestParam Integer UserId ) {
		return ResponseEntity.ok( new ResponseDto(false,"Appointments has listed successfully",appointmentService.getAppointmentsByDocterId(UserId)));
	}
	@GetMapping("/UpdateAppointment")
	public ResponseEntity<ResponseDto> updateAppointment(@RequestParam Integer applicationId ) {
		return ResponseEntity.ok( new ResponseDto(false,"Appointment has successfully updated",appointmentService.updateAppointment(applicationId)));
	}
}
