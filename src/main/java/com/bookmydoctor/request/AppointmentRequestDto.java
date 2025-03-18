package com.bookmydoctor.request;

import java.time.LocalDateTime;

import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class AppointmentRequestDto {
	
	@NotBlank(message = "User name is required")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "User email is required")
    @Email(message  = "Invalid email format")
    private String userEmail;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid phone number (must be 10 digits starting with 6, 7, 8, or 9)")
    private String phoneNumber;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1 year")
    @Max(value = 120, message = "Age must be less than 120 years")
    private Integer age;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Doctor is required")
    private Integer doctorId;
    @NotNull(message = "user is required")
    private Integer userId;
    

    @NotNull(message = "Appointment date and time is required")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDateTime;

    private AppointmentStatus status = AppointmentStatus.PENDING;

	

}
