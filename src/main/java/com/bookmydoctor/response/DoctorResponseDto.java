package com.bookmydoctor.response;

import com.bookmydoctor.enums.Gender;
import com.bookmydoctor.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Data
public class DoctorResponseDto {
	    private Integer doctorId;

	    private String doctorName;

	    private Double rating;

	    private String doctorNumber;


	    private String speciaList;


	    private Role role;


	    @Enumerated(EnumType.STRING)
	    private Gender gender;

	    private Integer ratingCount = 0; 

}
