package com.carloan.loanprocessing.service;

import com.carloan.loanprocessing.model.Loan;
import com.carloan.loanprocessing.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan apply(Loan loan) {
        loanRepository.save(loan);
        return loan;
    }
}
