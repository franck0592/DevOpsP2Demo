package com.revature.P1DemoBackend.dto;

public record OutGoingReimbursementDTO(Long reimbId, String description, double amount, String status, UserDTO userDto) {
}
