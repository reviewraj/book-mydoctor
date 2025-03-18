package com.bookmydoctor.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmydoctor.entity.Doctor;
import com.bookmydoctor.entity.LeaveRequest;
import com.bookmydoctor.enums.AppointmentStatus;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Integer> {
	 boolean existsByDoctorAndStartingDateLessThanEqualAndEndingDateGreaterThanEqualAndLeaveStatus(
		        Doctor doctor, LocalDate start, LocalDate end, AppointmentStatus status);
}
