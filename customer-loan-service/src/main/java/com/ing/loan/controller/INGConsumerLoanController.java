package com.ing.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.loan.dto.request.INGConsumerLoanRequest;
import com.ing.loan.dto.response.INGLoanResponse;
import com.ing.loan.model.INGConsumerEntity;
import com.ing.loan.service.INGConsumerLoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loan")
public class INGConsumerLoanController {

    @Autowired
    private INGConsumerLoanService customerLoanService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveLoanRequest(@RequestBody @Valid INGConsumerLoanRequest loanRequest) {
        INGConsumerEntity saveCustomerLoan = customerLoanService.saveConsumerLoan(loanRequest);
        return new ResponseEntity<>("The Loan Created Successfully for the customer : " 
        + saveCustomerLoan.getConsumerId(), HttpStatus.CREATED);
    }

    @GetMapping("/{consumerId}")
    public ResponseEntity<INGLoanResponse> getLoanByCustomerId(@PathVariable("consumerId") Long consumerId){
        return new ResponseEntity<>(customerLoanService.getConsumerLoanInformation(consumerId),HttpStatus.OK);
    }
}
