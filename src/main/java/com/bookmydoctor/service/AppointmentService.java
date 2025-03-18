package com.bookmydoctor.service;

import java.util.List;

import com.bookmydoctor.request.AppointmentRequestDto;
import com.bookmydoctor.response.AppointmentResponseDto;
import com.bookmydoctor.response.ResponseDto;

import jakarta.validation.Valid;

public interface AppointmentService {

	AppointmentResponseDto createAppointment( AppointmentRequestDto appointmentRequestDto);

	AppointmentResponseDto save(@Valid AppointmentRequestDto appointmentRequestDto);

	List<AppointmentResponseDto> getAppointmentsById(Integer userId);

	List<AppointmentResponseDto> getAppointmentsByDocterId(Integer userId);

	AppointmentResponseDto updateAppointment(Integer applicationId);

}
