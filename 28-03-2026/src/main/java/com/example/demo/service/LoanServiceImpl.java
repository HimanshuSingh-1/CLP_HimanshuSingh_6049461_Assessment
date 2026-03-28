package com.example.demo.service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepository;
import com.example.demo.exception.InvalidLoanAmountException;
import com.example.demo.exception.DuplicateLoanApplicationException;
import com.example.demo.exception.LoanNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan createLoan(Loan loan) {

       
        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }

     
        loanRepository.findByApplicantNameAndStatus(loan.getApplicantName(), "PENDING")
                .ifPresent(existingLoan -> {
                    throw new DuplicateLoanApplicationException("Applicant already has a pending loan");
                });

       
        loan.setStatus("PENDING");

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id " + id));
    }

    @Override
    public Loan updateLoanStatus(Long id, String status) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id " + id));

        loan.setStatus(status);
        return loanRepository.save(loan);
    }
}