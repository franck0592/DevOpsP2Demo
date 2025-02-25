package com.revature.P1DemoBackend.data;

import com.revature.P1DemoBackend.model.Reimbursement;
import com.revature.P1DemoBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {

    //JPQL query to find reimbursement by user
    List<Reimbursement> findByUserId(User userId);

    //JPQL query to find reimbursement by status
    List<Reimbursement> findByStatus(String status);

    //JPQL query to find reimbursement by status and user
    List<Reimbursement> findByStatusAndUserId(String status, User user);

}
