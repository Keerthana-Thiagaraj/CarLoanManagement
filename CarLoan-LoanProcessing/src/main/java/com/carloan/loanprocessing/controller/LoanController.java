package com.carloan.loanprocessing.controller;

import com.carloan.loanprocessing.client.UserProfileClient;
import com.carloan.loanprocessing.dto.LoanRequestDto;
import com.carloan.loanprocessing.exception.IneligibleUserException;
import com.carloan.loanprocessing.exception.UserProfileNotFoundException;
import com.carloan.loanprocessing.model.Loan;
import com.carloan.loanprocessing.model.LoanStatus;
import com.carloan.loanprocessing.model.LoanType;
import com.carloan.loanprocessing.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/api/v1/loan")
public class LoanController {

    @Autowired
    private UserProfileClient userProfileClient;

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyLoan(@RequestBody @Valid LoanRequestDto loanRequestDto) throws UserProfileNotFoundException, IneligibleUserException {
        ResponseEntity<String> entity;

        if (userProfileClient.findUserProfileById(loanRequestDto.getUserId()).isPresent()) {
            if (userProfileClient.getLoanEligibilityStatus(loanRequestDto.getUserId())) {
                Loan loan = new Loan(loanRequestDto.getUserId(), LoanType.INDIVIDUAL, LoanStatus.APPROVED, new Date(), loanRequestDto.getAmount());
                loanService.apply(loan);
                entity = new ResponseEntity(loan, HttpStatus.CREATED);
            } else
                throw new IneligibleUserException("Ineligible for loan processing. Please revisit the salary details!");
        } else
            throw new UserProfileNotFoundException("Invalid user id");
        return entity;
    }
}
