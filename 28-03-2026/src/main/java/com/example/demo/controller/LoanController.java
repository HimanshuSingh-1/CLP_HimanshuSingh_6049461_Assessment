package com.example.demo.controller;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.LoanStatusUpdate;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // POST /loans
    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    // GET /loans
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    // GET /loans/{id}
    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    // PUT /loans/{id}/status
//    @PutMapping("/{id}/status")
//    public Loan updateLoanStatus(@PathVariable Long id, @RequestBody Loan loan) {
//        return loanService.updateLoanStatus(id, loan.getStatus());
//    }
    
    


    @PutMapping("/{id}/status")
    public Loan updateLoanStatus(@PathVariable Long id,
                                 @RequestBody LoanStatusUpdate request) {
        return loanService.updateLoanStatus(id, request.getStatus());
    }
}