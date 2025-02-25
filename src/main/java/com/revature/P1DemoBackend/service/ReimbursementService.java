package com.revature.P1DemoBackend.service;

import com.revature.P1DemoBackend.dto.InComingReimbursementDTO;
import com.revature.P1DemoBackend.dto.OutGoingReimbursementDTO;
import com.revature.P1DemoBackend.exception.ReimbursementNotFoundException;
import com.revature.P1DemoBackend.model.Reimbursement;
import com.revature.P1DemoBackend.model.User;

import java.util.List;
import java.util.Optional;

public interface ReimbursementService {

    //All user tasks
    Optional<Reimbursement> createReimbursement(InComingReimbursementDTO reimbursementDto);
    Optional<List<OutGoingReimbursementDTO>> getAllReimbursementByUser(User userId) throws ReimbursementNotFoundException;
        Optional<List<OutGoingReimbursementDTO>> getAllPendingReimbursementByUser(User user) throws ReimbursementNotFoundException;

    //All Manager TASKS
    Optional<List<OutGoingReimbursementDTO>> getAllReimbursement();
    Optional<List<OutGoingReimbursementDTO>> getAllPendingReimbursement(String status);
    Optional<OutGoingReimbursementDTO> updateReimbursementPartially(InComingReimbursementDTO inComingReimbursementDTO) throws ReimbursementNotFoundException;

    Optional<Long> deleteReimbursement(long reimbId) throws ReimbursementNotFoundException;

}
