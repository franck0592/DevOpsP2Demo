package com.revature.P1DemoBackend.dto;
public record InComingReimbursementDTO(Long reimbId, String description, double amount, String status, Long userId) {
}
