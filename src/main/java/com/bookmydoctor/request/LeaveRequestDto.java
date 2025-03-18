package com.bookmydoctor.request;

import java.time.LocalDate;

import com.bookmydoctor.enums.AppointmentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaveRequestDto {
	
	@NotNull(message = "reason for the leave is required")
	private String reason;
	@NotNull(message = "startingdate for the leave is required")
	private LocalDate startingDate;
	@NotNull(message = "endingdate for the leave is required")
	private LocalDate endingDate;
	@NotNull(message = "doctor email is required to find the doctor")
	private String email;
	private AppointmentStatus appointmentStatus = AppointmentStatus.PENDING;
}
