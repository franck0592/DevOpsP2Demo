package com.revature.P1DemoBackend.mapper;

import com.revature.P1DemoBackend.dto.InComingReimbursementDTO;
import com.revature.P1DemoBackend.model.Reimbursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReimbursementMapper {
    @Mapping(source = "reimbursementDto.amount", target = "amount")
    InComingReimbursementDTO reimbursementDtoToReimbursement(InComingReimbursementDTO reimbursementDto);
    @Mapping(source = "reimbursement.amount", target = "amount")
    Reimbursement reimbursementToReimbursementDto(Reimbursement reimbursement);
}
