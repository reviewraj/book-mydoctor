package com.bookmydoctor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bookmydoctor.entity.Appointment;
import com.bookmydoctor.entity.Doctor;
import com.bookmydoctor.entity.User;
import com.bookmydoctor.enums.AppointmentStatus;
import com.bookmydoctor.enums.Status;
import com.bookmydoctor.exception.NoAppointmentsFound;
import com.bookmydoctor.exception.NoDoctorsAvailable;
import com.bookmydoctor.exception.UserNotExist;
import com.bookmydoctor.repository.AppointmentRepository;
import com.bookmydoctor.repository.DoctorRepository;
import com.bookmydoctor.repository.UserRepository;
import com.bookmydoctor.request.AppointmentRequestDto;
import com.bookmydoctor.response.AppointmentResponseDto;
import com.bookmydoctor.response.DoctorResponseDto;
import com.bookmydoctor.response.UserResponseDto;

import jakarta.validation.Valid;
@Service
public class AppointmentServiceImpl implements AppointmentService {
   @Autowired
	private AppointmentRepository appointmentRepository;
   @Autowired
  	private DoctorRepository doctorRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
 	private ModelMapper modelMapper;
   @Autowired
   private JavaMailSender mailSender;
	@Override
	public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentRequestDto) {
		Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentRequestDto.getDoctorId());
		if(doctorOptional.isEmpty()){
		throw new NoDoctorsAvailable("doctor is not available right now");	
		}
		Optional<User> byUserEmail = userRepository.findByUserEmail(appointmentRequestDto.getUserEmail());
		if(byUserEmail.isEmpty()||byUserEmail.get().getStatus()==Status.INACTIVE)throw new UserNotExist("Please enter valid emails");	
		Appointment appointment = new Appointment();	
		BeanUtils.copyProperties(appointmentRequestDto, appointment);
		appointment.setDoctor(doctorOptional.get());
	    appointment.setUser(byUserEmail.get());
	    System.out.println(appointment);
		Appointment save = appointmentRepository.save(appointment);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(save.getUser(), userResponseDto);
		DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
		modelMapper.map(save.getDoctor(), doctorResponseDto);
		AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
		modelMapper.map(save, appointmentResponseDto);
		appointmentResponseDto.setDoctorResponseDto(doctorResponseDto);
		appointmentResponseDto.setUserResponseDto(userResponseDto);
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appointment.getUserEmail());
message.setSubject("Appointment Confirmation - BookMyDoctor");
        
        String emailText = String.format("""
            Hi %s,
            
            Your appointment has been successfully booked.
            
            📅 Appointment Date & Time: %s
            
            👨‍⚕️ Doctor Details:
            - Name: %s
            - Specialty: %s
            - Contact: %s
            - Rating: %.2f ⭐
            
            📌 Appointment Status: %s
            
            If you have any questions, feel free to contact us.
            
            Regards,
            BookMyDoctor Team
        """,
        appointmentResponseDto.getUserName(),
        appointmentResponseDto.getAppointmentDateTime(),
        appointmentResponseDto.getDoctorResponseDto().getDoctorName(),
        appointmentResponseDto.getDoctorResponseDto().getSpeciaList(),
        appointmentResponseDto.getDoctorResponseDto().getDoctorNumber(),
        appointmentResponseDto.getDoctorResponseDto().getRating(),
        appointmentResponseDto.getStatus());

        message.setText(emailText);
        mailSender.send(message);
		return appointmentResponseDto;	
	}
	@Override
	public AppointmentResponseDto save(@Valid AppointmentRequestDto appointmentRequestDto) {
		Appointment appointment = new Appointment();
		System.out.println(appointmentRequestDto);
		modelMapper.map(appointmentRequestDto, appointment);
	    System.out.println(appointment);
//	    appointment.setAppointmentId(null);
		Appointment save = appointmentRepository.save(appointment);
		AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
		modelMapper.map(save, appointmentResponseDto);
		return appointmentResponseDto;	
	}
	@Override
	public List<AppointmentResponseDto> getAppointmentsById(Integer userId) {
		List<Appointment> all = appointmentRepository.findAll();
		List<Appointment> list = all.stream().filter(e->e.getUser().getUserId()==userId).toList();
		if(list.isEmpty())throw new NoAppointmentsFound("no appointments found");
		List<AppointmentResponseDto>appointmentResponseDtos = new ArrayList<>();
		for (Appointment appointment : list) {
			AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
			modelMapper.map(appointment, appointmentResponseDto);
			appointmentResponseDtos.add(appointmentResponseDto);
		}
		return appointmentResponseDtos;
	}
	@Override
	public List<AppointmentResponseDto> getAppointmentsByDocterId(Integer userId) {
		List<Appointment> all = appointmentRepository.findAll();
		List<Appointment> list = all.stream().filter(e->e.getDoctor().getDoctorId()==userId&&e.getStatus()==AppointmentStatus.ACCEPTED).toList();
		if(list.isEmpty())throw new NoAppointmentsFound("no appointments found");
		List<AppointmentResponseDto>appointmentResponseDtos = new ArrayList<>();
		for (Appointment appointment : list) {
			AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
			UserResponseDto userResponseDto = new UserResponseDto();
			modelMapper.map(appointment.getUser(), userResponseDto);
			DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
			modelMapper.map(appointment.getDoctor(), doctorResponseDto);
			modelMapper.map(appointment, appointmentResponseDto);
			appointmentResponseDto.setUserResponseDto(userResponseDto);
			appointmentResponseDto.setDoctorResponseDto(doctorResponseDto);
			appointmentResponseDtos.add(appointmentResponseDto);
		}
		return appointmentResponseDtos;
	}
	@Override
	public AppointmentResponseDto updateAppointment(Integer applicationId) {
		Optional<Appointment> optional = appointmentRepository.findById(applicationId);
		if(optional.isEmpty())throw new NoAppointmentsFound("no appointments found");
		Appointment appointment = optional.get();
		appointment.setStatus(AppointmentStatus.ACCEPTED);
		Appointment save = appointmentRepository.save(appointment);
		
			AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
			UserResponseDto userResponseDto = new UserResponseDto();
			modelMapper.map(save.getUser(), userResponseDto);
			DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
			modelMapper.map(save.getDoctor(), doctorResponseDto);
			modelMapper.map(save, appointmentResponseDto);
			appointmentResponseDto.setUserResponseDto(userResponseDto);
			appointmentResponseDto.setDoctorResponseDto(doctorResponseDto);
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(appointment.getUserEmail());
	message.setSubject("Appointment Confirmation - BookMyDoctor");
	        
	        String emailText = String.format("""
	            Hi %s,
	            
	            Your appointment has been successfully booked.
	            
	            📅 Appointment Date & Time: %s
	            
	            👨‍⚕️ Doctor Details:
	            - Name: %s
	            - Specialty: %s
	            - Contact: %s
	            - Rating: %.2f ⭐
	            
	            📌 Appointment Status: %s
	            
	            If you have any questions, feel free to contact us.
	            
	            Regards,
	            BookMyDoctor Team
	        """,
	        appointmentResponseDto.getUserName(),
	        appointmentResponseDto.getAppointmentDateTime(),
	        appointmentResponseDto.getDoctorResponseDto().getDoctorName(),
	        appointmentResponseDto.getDoctorResponseDto().getSpeciaList(),
	        appointmentResponseDto.getDoctorResponseDto().getDoctorNumber(),
	        appointmentResponseDto.getDoctorResponseDto().getRating(),
	        appointmentResponseDto.getStatus());

	        message.setText(emailText);
	        mailSender.send(message);
			return appointmentResponseDto;
		}
		
	

}
