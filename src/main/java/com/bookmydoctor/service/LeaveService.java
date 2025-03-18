package com.bookmydoctor.service;

import com.bookmydoctor.request.LeaveRequestDto;
import com.bookmydoctor.response.LeaveResponseDto;

public interface LeaveService {

	LeaveResponseDto leaveRequest(LeaveRequestDto leaveRequestDto);

	LeaveResponseDto approveRequest(Integer leaveId);

}
