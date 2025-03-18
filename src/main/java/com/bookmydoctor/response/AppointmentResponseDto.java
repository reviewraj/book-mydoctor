package com.bookmydoctor.response;

import java.time.LocalDateTime;

import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.enums.Gender;

import lombok.Data;
@Data
public class AppointmentResponseDto {
    private String userName;

    private String userEmail;

    private String phoneNumber;

    private Integer age;

    private Gender gender;

    private DoctorResponseDto doctorResponseDto;
    
    private UserResponseDto userResponseDto;

    private LocalDateTime appointmentDateTime;

    private AppointmentStatus status = AppointmentStatus.PENDING;

	


}
