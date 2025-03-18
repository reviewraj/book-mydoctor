package com.bookmydoctor.entity;

import java.time.LocalDateTime;

import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "appointments")
public class Appointment extends Auditable{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "appointment_id")
	    private Integer appointmentId;

	    @Column(name = "username", nullable = false)
	    private String userName;

	    @Column(name = "user_email", nullable = false)
	    private String userEmail;

	    @Column(name = "user_number", nullable = false)
	    private String phoneNumber;

	    @Column(nullable = false)
	    private Integer age;

	    @Enumerated(EnumType.STRING)
	    private Gender gender;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "doctor_id", nullable = false)
	    private Doctor doctor; 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "User_id", nullable = false)
	    private User user; 

	    @Column(name = "appointment_datetime", nullable = false)
	    private LocalDateTime appointmentDateTime; 

	    @Enumerated(EnumType.STRING)
	    @Column(name = "status", nullable = false)
	    private AppointmentStatus status = AppointmentStatus.PENDING;

}
