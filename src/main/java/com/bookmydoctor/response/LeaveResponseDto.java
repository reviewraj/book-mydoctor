package com.bookmydoctor.response;

import com.bookmydoctor.enums.AppointmentStatus;

import lombok.Data;

@Data
public class LeaveResponseDto {
	private Integer leaveId;
	private String reason;
	private DoctorResponseDto doctor;
	private AppointmentStatus appointmentStatus = AppointmentStatus.PENDING;
}
