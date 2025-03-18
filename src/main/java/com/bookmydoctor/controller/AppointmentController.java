package com.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/bookmydoctor/api/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService  appointmentService;
	
	@PostMapping("/book")
	public ResponseDto bookAppointment(@RequestBody @Valid AppointmentRequestDto appointmentRequestDto ) {
		return new ResponseDto(false,"Appointment has requested successfully",appointmentService.createAppointment(appointmentRequestDto));
	}
	@GetMapping("/getByUserId")
	public ResponseDto getByUserId(@RequestParam Integer UserId ) {
		return new ResponseDto(false,"Appointment has listed successfully",appointmentService.getAppointmentsById(UserId));
	}
	@GetMapping("/getByDocterId")
	public ResponseDto getByDocterId(@RequestParam Integer UserId ) {
		return new ResponseDto(false,"Appointments has listed successfully",appointmentService.getAppointmentsByDocterId(UserId));
	}
	@GetMapping("/UpdateAppointment")
	public ResponseDto updateAppointment(@RequestParam Integer applicationId ) {
		return new ResponseDto(false,"Appointment has successfully updated",appointmentService.updateAppointment(applicationId));
	}
}
