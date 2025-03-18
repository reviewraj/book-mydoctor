package com.bookmydoctor.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmydoctor.request.DoctorRequestDto;
import com.bookmydoctor.response.ResponseDto;
import com.bookmydoctor.service.DoctorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/bookmydoctor/api/doctor")
public class DoctorConctroller {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DoctorService  doctorService;
	
	@PostMapping("/save")
	public ResponseDto saveUser(@RequestBody @Valid DoctorRequestDto doctorRequestDto ) {
		return new ResponseDto(false,"doctor data created successfully",doctorService.save(doctorRequestDto));
	}
	@DeleteMapping("/delete")
	public ResponseDto deleteUser(@RequestBody DoctorRequestDto doctorRequestDto ) {
		return new ResponseDto(false,"doctor data  deleted successfully",doctorService.delete(doctorRequestDto));
	}
	@GetMapping("/getAll")
	public ResponseDto getAllUsers( ) {
		return new ResponseDto(false,"doctors listed successfully",doctorService.getAll());
	}
	@GetMapping("/search")
    public ResponseDto searchDoctors(
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String speciaList,
            @RequestParam(required = false) Double minRating) {
            
           return new ResponseDto(false,"please find the list below", doctorService.searchDoctors(doctorName, speciaList, minRating));
            
        }
	@GetMapping("/rating")
    public ResponseDto searchDoctors(@RequestParam  Double rating,@RequestParam String doctorEmail) {
            
           return new ResponseDto(false,"please find the list below", doctorService.rateTheDoctor(rating,doctorEmail));
            
        }

}
