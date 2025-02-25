package com.revature.P1DemoBackend.controller;


import com.revature.P1DemoBackend.dto.InComingReimbursementDTO;
import com.revature.P1DemoBackend.dto.OutGoingReimbursementDTO;
import com.revature.P1DemoBackend.exception.ReimbursementNotFoundException;
import com.revature.P1DemoBackend.model.Reimbursement;
import com.revature.P1DemoBackend.model.User;
import com.revature.P1DemoBackend.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "false") //Allows request that came from somewhere
public class ReimbursementController {

    private ReimbursementService reimbursementService;
@Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @PostMapping
    public ResponseEntity<Reimbursement> addReimbursementHandler(@RequestBody InComingReimbursementDTO reimbursementDto){
       Optional<Reimbursement > reimbursementCreated=reimbursementService.createReimbursement(reimbursementDto);
       if (reimbursementCreated.isPresent()){
           return ResponseEntity.status(HttpStatus.CREATED).body(reimbursementCreated.get());
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping
    public ResponseEntity<List<OutGoingReimbursementDTO>> getAllReimbursementHandler(){
        List<OutGoingReimbursementDTO> emptyReimbursement=new ArrayList<>();
        Optional<List<OutGoingReimbursementDTO>> reimbursementsRetrieved=reimbursementService.getAllReimbursement();
        if(reimbursementsRetrieved.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(reimbursementsRetrieved.get());
        }
       return ResponseEntity.status(HttpStatus.OK).body(emptyReimbursement);
   }

    @GetMapping("/pending")
    public ResponseEntity<List<OutGoingReimbursementDTO>> getAllPendingReimbursementHandler(@RequestParam String status){
        List<OutGoingReimbursementDTO> emptyReimbursement=new ArrayList<>();
        Optional<List<OutGoingReimbursementDTO>> pendingReimbursementsRetrieved=reimbursementService.getAllPendingReimbursement(status);
        if(pendingReimbursementsRetrieved.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(pendingReimbursementsRetrieved.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(emptyReimbursement);
    }
    @PostMapping("/user")
   public ResponseEntity<List<OutGoingReimbursementDTO>> getAllReimbursementByUserHandler(@RequestBody User userId){
    List<OutGoingReimbursementDTO> emptyReimbursementByUser=new ArrayList<>();
    Optional<List<OutGoingReimbursementDTO>> reimbursementsByUser=reimbursementService.getAllReimbursementByUser(userId);

    if (reimbursementsByUser.isPresent()){
        return ResponseEntity.status(HttpStatus.OK).body(reimbursementsByUser.get());
    }
    return ResponseEntity.status(HttpStatus.OK).body(emptyReimbursementByUser);
   }

    @PostMapping("/user/pending")
    public ResponseEntity<List<OutGoingReimbursementDTO>> getAllPendingReimbursementByUserHandler(@RequestBody User userId){
        List<OutGoingReimbursementDTO> emptyReimbursementByUser=new ArrayList<>();
        Optional<List<OutGoingReimbursementDTO>> reimbursementsByUser=reimbursementService.getAllPendingReimbursementByUser(userId);

        if (reimbursementsByUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(reimbursementsByUser.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(emptyReimbursementByUser);
    }
    @PatchMapping
    public ResponseEntity<OutGoingReimbursementDTO> updateReimbursementPartially(@RequestBody InComingReimbursementDTO reimbursement){
    Optional<OutGoingReimbursementDTO> reimbursementUpdated=reimbursementService.updateReimbursementPartially(reimbursement);
    if (reimbursementUpdated.isPresent()){
        return ResponseEntity.status(HttpStatus.OK).body(reimbursementUpdated.get());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
@DeleteMapping("/{reimbId}")
public ResponseEntity<Void> deleteReimbursemenent(@PathVariable long reimbId){
        Optional<Long> isdeleted=reimbursementService.deleteReimbursement(reimbId);
        if(isdeleted.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
       throw new ReimbursementNotFoundException("reimbursement not found");
}

}




