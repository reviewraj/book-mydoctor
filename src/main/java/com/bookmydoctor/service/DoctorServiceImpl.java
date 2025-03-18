package com.bookmydoctor.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmydoctor.entity.Doctor;
import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.enums.IsWorking;
import com.bookmydoctor.enums.Role;
import com.bookmydoctor.enums.Status;
import com.bookmydoctor.exception.DoctorAlreadyExists;
import com.bookmydoctor.exception.NoDoctorsAvailable;
import com.bookmydoctor.exception.PasswordMismatch;
import com.bookmydoctor.exception.UserNotExist;
import com.bookmydoctor.repository.DoctorRepository;
import com.bookmydoctor.repository.LeaveRepository;
import com.bookmydoctor.request.DoctorRequestDto;
import com.bookmydoctor.response.DoctorResponseDto;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private LeaveRepository leaveRepository;

	@Override
	public DoctorResponseDto save(DoctorRequestDto doctorRequestDto) {
		Optional<Doctor> byDoctorEmail = doctorRepository.findByDoctorEmail(doctorRequestDto.getDoctorEmail());
		if (byDoctorEmail.isEmpty()) {
			Doctor doctor = new Doctor();
			modelMapper.map(doctorRequestDto, doctor);
			doctor.setRole(Role.DOCTOR);
			Doctor save = doctorRepository.save(doctor);
			DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
			modelMapper.map(save,doctorResponseDto );
			return doctorResponseDto;

		}
		else {
			throw new DoctorAlreadyExists("doctor is already exists with the email "+doctorRequestDto.getDoctorEmail());
		}
		
		
	}

//	@Override
//	public DoctorResponseDto update(DoctorRequestDto doctorRequestDto) {
//		return null;
//	}

	@Override
	public DoctorResponseDto delete(DoctorRequestDto doctorRequestDto) {
		Optional<Doctor> optional = doctorRepository.findByDoctorEmail(doctorRequestDto.getDoctorEmail());
		if (optional.isPresent())
			throw new UserNotExist("doctor not exits with this email : " + doctorRequestDto.getDoctorEmail());

		else if (!optional.get().getDoctorPassword().equals(doctorRequestDto.getDoctorPassword())) {
			throw new PasswordMismatch("please enter associated password with : " + doctorRequestDto.getDoctorEmail());
		}
		Doctor userEntity = optional.get();
		userEntity.setStatus(Status.INACTIVE);
		Doctor dbUser = doctorRepository.save(userEntity);
		DoctorResponseDto userResponseDto = new DoctorResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		return userResponseDto;
	}

	@Override
	public List<DoctorResponseDto> getAll() {
		List<Doctor> doctorList = doctorRepository.findAll();
		LocalDate today = LocalDate.now();
		if(doctorList.isEmpty()) throw new NoDoctorsAvailable("no doctors available Now ");
		doctorList = doctorList.stream().filter(doctor->doctor.getStatus()==Status.ACTIVE&&doctor.getIsWorking()==IsWorking.TRUE&& !isDoctorOnLeave(doctor, today)).toList();
		List<DoctorResponseDto> doctorResponseDtos= new ArrayList<>();
		for (Doctor doctor : doctorList) {
			DoctorResponseDto doctorRequestDto2 = new DoctorResponseDto();
			modelMapper.map(doctor, doctorRequestDto2);
			doctorResponseDtos.add(doctorRequestDto2);
		}
		
		return doctorResponseDtos;
	}

	@Override
	public List<DoctorResponseDto> searchDoctors(String doctorName, String speciaList, Double minRating) {
		 List<Doctor> searchDoctors = doctorRepository.searchDoctors(doctorName, speciaList, minRating);
		 LocalDate today = LocalDate.now();
		 searchDoctors = searchDoctors.stream().filter(doctor->doctor.getStatus()==Status.ACTIVE&&doctor.getIsWorking()==IsWorking.TRUE&& !isDoctorOnLeave(doctor, today)).toList();
		 if(searchDoctors.isEmpty()) throw new NoDoctorsAvailable("no doctors available Now ");
		 List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();
         for(Doctor doc:searchDoctors) {
        	 DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        	 modelMapper.map(doc, doctorResponseDto);
        	 doctorResponseDtos.add(doctorResponseDto);
         }
         return doctorResponseDtos;
	}

	@Override
	public DoctorResponseDto rateTheDoctor(Double rating, String doctorEmail) {
		Optional<Doctor> byDoctorEmail = doctorRepository.findByDoctorEmail(doctorEmail);
		if(byDoctorEmail.isEmpty())throw new NoDoctorsAvailable("no doctors found Now with this email : "+doctorEmail);
	 byDoctorEmail.get().addRating(rating);
	 Doctor doctor = byDoctorEmail.get();
	 Doctor save = doctorRepository.save(doctor);
	 DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
		modelMapper.map(save,doctorResponseDto );
		return doctorResponseDto;
	}
	private boolean isDoctorOnLeave(Doctor doctor, LocalDate today) {
	    return leaveRepository.existsByDoctorAndStartingDateLessThanEqualAndEndingDateGreaterThanEqualAndLeaveStatus(
	        doctor, today, today, AppointmentStatus.ACCEPTED); 
	}

}
