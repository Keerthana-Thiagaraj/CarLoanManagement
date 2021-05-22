package com.carloan.loanprocessing.repository;

import com.carloan.loanprocessing.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {

}
