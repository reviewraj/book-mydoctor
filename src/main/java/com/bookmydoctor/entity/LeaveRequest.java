package com.bookmydoctor.entity;

import java.time.LocalDate;

import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class LeaveRequest extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leaveId")
	private Integer leaveId;

	private LocalDate startingDate;

	private LocalDate endingDate;

	@Column(nullable = false)
	private String reason;

	@JoinColumn(name = "doctor_id")
	@ManyToOne
	private Doctor doctor;
    @Enumerated(EnumType.STRING)
	private AppointmentStatus leaveStatus = AppointmentStatus.PENDING;
}
