package com.revature.P1DemoBackend.service.implementation;

import com.revature.P1DemoBackend.data.ReimbursementRepository;
import com.revature.P1DemoBackend.data.UserRepository;
import com.revature.P1DemoBackend.dto.InComingReimbursementDTO;
import com.revature.P1DemoBackend.dto.OutGoingReimbursementDTO;
import com.revature.P1DemoBackend.dto.UserDTO;
import com.revature.P1DemoBackend.exception.ReimbursementNotFoundException;
import com.revature.P1DemoBackend.exception.UserNotFoundException;
import com.revature.P1DemoBackend.model.Reimbursement;
import com.revature.P1DemoBackend.model.User;
import com.revature.P1DemoBackend.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ReimbursementServiceImplementation implements ReimbursementService {

    private ReimbursementRepository reimbursementRepository;
    private UserRepository userRepository;

@Autowired
    public ReimbursementServiceImplementation(ReimbursementRepository reimbursementRepository, UserRepository userRepository) {
        this.reimbursementRepository = reimbursementRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Reimbursement> createReimbursement(InComingReimbursementDTO inComingReimbursementDTO) {
        Reimbursement newReimbursement=new Reimbursement();
        newReimbursement.setDescription(inComingReimbursementDTO.description());
        newReimbursement.setAmount(inComingReimbursementDTO.amount());
        Optional<User> user=userRepository.findById(inComingReimbursementDTO.userId());
        if(user.isPresent()){
            newReimbursement.setUserId(user.get());
            Reimbursement reimbursementCreated=reimbursementRepository.save(newReimbursement);
            return Optional.of(reimbursementCreated);
        }else{
            throw new UserNotFoundException("User not found!");
        }
    }

    @Override
    public Optional<List<OutGoingReimbursementDTO>> getAllReimbursementByUser(User userId) throws ReimbursementNotFoundException {
        List<Reimbursement> reimbursementListByUser = reimbursementRepository.findByUserId(userId);

        // Check if the list is empty
        if (reimbursementListByUser.isEmpty()) {
            throw new ReimbursementNotFoundException("No reimbursements found for this user.");
        }

        // Create a list to hold the DTOs
        List<OutGoingReimbursementDTO> outGoingReimbursementDTOList = new ArrayList<>();

        // Loop through each reimbursement and create corresponding DTO
        for (Reimbursement reimbursement : reimbursementListByUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(reimbursement.getUserId().getUserId());
            userDTO.setFirstName(reimbursement.getUserId().getFirstName());
            userDTO.setLastName(reimbursement.getUserId().getLastName());
            userDTO.setUsername(reimbursement.getUserId().getUsername());
            userDTO.setRole(reimbursement.getUserId().getRole());

            OutGoingReimbursementDTO outGoingReimbursementDTO = new OutGoingReimbursementDTO(
                    reimbursement.getReImbId(),
                    reimbursement.getDescription(),
                    reimbursement.getAmount(),
                    reimbursement.getStatus(),
                    userDTO
            );

            outGoingReimbursementDTOList.add(outGoingReimbursementDTO);
        }

        // Return the list wrapped in an Optional
        return Optional.of(outGoingReimbursementDTOList);
    }


    @Override
    public Optional<List<OutGoingReimbursementDTO>> getAllPendingReimbursementByUser(User user) throws ReimbursementNotFoundException {
        String status="Pending";
        List<Reimbursement> reimbursementList=reimbursementRepository.findByStatusAndUserId(status,user);
        // Check if the list is empty
        if (reimbursementList.isEmpty()) {
            throw new ReimbursementNotFoundException("No pending reimbursements found for this user.");
        }

        // Create a list to hold the DTOs
        List<OutGoingReimbursementDTO> outGoingReimbursementDTOList = new ArrayList<>();

        // Loop through each reimbursement and create corresponding DTO
        for (Reimbursement reimbursement : reimbursementList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(reimbursement.getUserId().getUserId());
            userDTO.setFirstName(reimbursement.getUserId().getFirstName());
            userDTO.setLastName(reimbursement.getUserId().getLastName());
            userDTO.setUsername(reimbursement.getUserId().getUsername());
            userDTO.setRole(reimbursement.getUserId().getRole());

            OutGoingReimbursementDTO outGoingReimbursementDTO = new OutGoingReimbursementDTO(
                    reimbursement.getReImbId(),
                    reimbursement.getDescription(),
                    reimbursement.getAmount(),
                    reimbursement.getStatus(),
                    userDTO
            );

            outGoingReimbursementDTOList.add(outGoingReimbursementDTO);
        }

        // Return the list wrapped in an Optional
        return Optional.of(outGoingReimbursementDTOList);

    }

    @Override
    public Optional<List<OutGoingReimbursementDTO>> getAllReimbursement() {
        List<Reimbursement> reimbursementList=reimbursementRepository.findAll();

        // Check if the list is empty
        if (reimbursementList.isEmpty()) {
            throw new ReimbursementNotFoundException("No reimbursements found for this user.");
        }

        // Create a list to hold the DTOs
        List<OutGoingReimbursementDTO> outGoingReimbursementDTOList = new ArrayList<>();

        // Loop through each reimbursement and create corresponding DTO
        for (Reimbursement reimbursement : reimbursementList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(reimbursement.getUserId().getUserId());
            userDTO.setFirstName(reimbursement.getUserId().getFirstName());
            userDTO.setLastName(reimbursement.getUserId().getLastName());
            userDTO.setUsername(reimbursement.getUserId().getUsername());
            userDTO.setRole(reimbursement.getUserId().getRole());

            OutGoingReimbursementDTO outGoingReimbursementDTO = new OutGoingReimbursementDTO(
                    reimbursement.getReImbId(),
                    reimbursement.getDescription(),
                    reimbursement.getAmount(),
                    reimbursement.getStatus(),
                    userDTO
            );

            outGoingReimbursementDTOList.add(outGoingReimbursementDTO);
        }

        // Return the list wrapped in an Optional
        return Optional.of(outGoingReimbursementDTOList);
    }

    @Override
    public Optional<List<OutGoingReimbursementDTO>> getAllPendingReimbursement(String status) {
        List<Reimbursement> reimbursementList=reimbursementRepository.findByStatus(status);

        // Check if the list is empty
        if (reimbursementList.isEmpty()) {
            throw new ReimbursementNotFoundException("No pending reimbursement(s) found.");
        }

        // Create a list to hold the DTOs
        List<OutGoingReimbursementDTO> outGoingReimbursementDTOList = new ArrayList<>();

        // Loop through each reimbursement and create corresponding DTO
        for (Reimbursement reimbursement : reimbursementList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(reimbursement.getUserId().getUserId());
            userDTO.setFirstName(reimbursement.getUserId().getFirstName());
            userDTO.setLastName(reimbursement.getUserId().getLastName());
            userDTO.setUsername(reimbursement.getUserId().getUsername());
            userDTO.setRole(reimbursement.getUserId().getRole());

            OutGoingReimbursementDTO outGoingReimbursementDTO = new OutGoingReimbursementDTO(
                    reimbursement.getReImbId(),
                    reimbursement.getDescription(),
                    reimbursement.getAmount(),
                    reimbursement.getStatus(),
                    userDTO
            );

            outGoingReimbursementDTOList.add(outGoingReimbursementDTO);
        }

        // Return the list wrapped in an Optional
        return Optional.of(outGoingReimbursementDTOList);
    }

    @Override
    public Optional<OutGoingReimbursementDTO> updateReimbursementPartially(InComingReimbursementDTO inComingReimbursementDTO) throws ReimbursementNotFoundException {
    Optional<Reimbursement> reimbursementRetreived=reimbursementRepository.findById(inComingReimbursementDTO.reimbId());
        if (reimbursementRetreived.isPresent()){
           Reimbursement reimbursement=reimbursementRetreived.get();
            reimbursement.setDescription(inComingReimbursementDTO.description());
            reimbursement.setAmount(inComingReimbursementDTO.amount());
            reimbursement.setStatus(inComingReimbursementDTO.status());
            Reimbursement savedreimbursement= reimbursementRepository.save(reimbursement);

            //convert to user DTO
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(savedreimbursement.getUserId().getUserId());
            userDTO.setFirstName(savedreimbursement.getUserId().getFirstName());
            userDTO.setLastName(savedreimbursement.getUserId().getLastName());
            userDTO.setUsername(savedreimbursement.getUserId().getUsername());
            userDTO.setRole(savedreimbursement.getUserId().getRole());

            OutGoingReimbursementDTO outGoingReimbursementDTO = new OutGoingReimbursementDTO(
                    savedreimbursement.getReImbId(),
                    savedreimbursement.getDescription(),
                    savedreimbursement.getAmount(),
                    savedreimbursement.getStatus(),
                    userDTO
            );
            return Optional.of(outGoingReimbursementDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> deleteReimbursement(long reimbId) throws ReimbursementNotFoundException {
        Long recordFound= Long.valueOf(0);
        Optional<Reimbursement> reimbursementRetreived=reimbursementRepository.findById(reimbId);
        if(reimbursementRetreived.isPresent()){
            reimbursementRepository.delete(reimbursementRetreived.get());
            recordFound= 1L;
            return Optional.of(recordFound);
        }
        throw new ReimbursementNotFoundException("Reimbursement not found.");
    }
}
