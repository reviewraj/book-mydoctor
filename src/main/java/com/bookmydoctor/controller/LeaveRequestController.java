package com.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmydoctor.request.LeaveRequestDto;
import com.bookmydoctor.response.ResponseDto;
import com.bookmydoctor.service.LeaveService;

@RestController
@RequestMapping("/bookmydoctor/api/leave")
public class LeaveRequestController {
	@Autowired
	private LeaveService leaveService;

	@PostMapping("/request")
	public ResponseEntity<ResponseDto> leaveRequest(@RequestBody LeaveRequestDto leaveRequestDto) {
		return ResponseEntity.ok (new ResponseDto(false, "leave is request succesfully", leaveService.leaveRequest(leaveRequestDto)));
	}

	@PostMapping("/approveLeave")
	public ResponseEntity<ResponseDto> leaveRequest(@RequestParam Integer leaveId) {
		return ResponseEntity.ok ( new ResponseDto(false, "leave is request succesfully", leaveService.approveRequest(leaveId)));
	}

}
