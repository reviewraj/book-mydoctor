package com.bookmydoctor.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmydoctor.entity.Doctor;
import com.bookmydoctor.entity.LeaveRequest;
import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.exception.NoDoctorsAvailable;
import com.bookmydoctor.exception.UserNotExist;
import com.bookmydoctor.repository.DoctorRepository;
import com.bookmydoctor.repository.LeaveRepository;
import com.bookmydoctor.request.LeaveRequestDto;
import com.bookmydoctor.response.DoctorResponseDto;
import com.bookmydoctor.response.LeaveResponseDto;

@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public LeaveResponseDto leaveRequest(LeaveRequestDto leaveRequestDto) {
		Optional<Doctor> byDoctorEmail = doctorRepository.findByDoctorEmail(leaveRequestDto.getEmail());
		if (byDoctorEmail.isEmpty())
			throw new NoDoctorsAvailable("please enter the valid email ");
		Doctor doctor = byDoctorEmail.get();
		LeaveRequest leave = new LeaveRequest();
		modelMapper.map(leaveRequestDto, leave);
		leave.setDoctor(doctor);
		leave.setLeaveStatus(AppointmentStatus.PENDING);
		LeaveRequest save = leaveRepository.save(leave);
		LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
		modelMapper.map(leave, leaveResponseDto);
		Doctor doctor2 = leave.getDoctor();
		DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
		modelMapper.map(doctor2, doctorResponseDto);
		leaveResponseDto.setDoctor(doctorResponseDto);
		return leaveResponseDto;

	}

	@Override
	public LeaveResponseDto approveRequest(Integer leaveId) {

		Optional<LeaveRequest> byId = leaveRepository.findById(leaveId);
		if (byId.isEmpty())
			throw new UserNotExist("leave not exist");
		LeaveRequest leaveRequest = byId.get();
		leaveRequest.setLeaveStatus(AppointmentStatus.ACCEPTED);
		LeaveRequest save = leaveRepository.save(leaveRequest);
		LeaveResponseDto leaveResponseDto = new LeaveResponseDto();
		modelMapper.map(save, leaveResponseDto);
		Doctor doctor2 = save.getDoctor();
		DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
		modelMapper.map(doctor2, doctorResponseDto);
		leaveResponseDto.setDoctor(doctorResponseDto);
		return leaveResponseDto;
	}

}
