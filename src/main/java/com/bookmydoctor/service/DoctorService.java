package com.bookmydoctor.service;

import java.util.List;

import com.bookmydoctor.entity.Doctor;
import com.bookmydoctor.enums.Status;
import com.bookmydoctor.request.DoctorRequestDto;
import com.bookmydoctor.response.DoctorResponseDto;

public interface DoctorService {

	 DoctorResponseDto save(DoctorRequestDto doctorRequestDto);

//	 DoctorResponseDto update(DoctorRequestDto doctorRequestDto);

	 DoctorResponseDto delete(DoctorRequestDto doctorRequestDto);
	 List<DoctorResponseDto> getAll();

	List<DoctorResponseDto> searchDoctors(String doctorName, String speciaList, Double minRating);

	DoctorResponseDto rateTheDoctor(Double rating, String doctorEmail);
}
