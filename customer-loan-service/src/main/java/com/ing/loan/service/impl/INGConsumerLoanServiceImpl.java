package com.ing.loan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.loan.dto.request.INGConsumerLoanRequest;
import com.ing.loan.dto.response.INGLoanResponse;
import com.ing.loan.exception.UsernameNotFoundException;
import com.ing.loan.model.INGConsumerEntity;
import com.ing.loan.model.INGConsumerLoanEntity;
import com.ing.loan.repository.INGConsumerLoanRepository;
import com.ing.loan.service.INGConsumerLoanService;

@Service
public class INGConsumerLoanServiceImpl implements INGConsumerLoanService{

    @Autowired
    private INGConsumerLoanRepository consumerLoanRepository;

    public INGConsumerEntity saveConsumerLoan(INGConsumerLoanRequest loanRequest) {
        INGConsumerEntity consumer = prepareConsumerLoanRequest(loanRequest);
        return consumerLoanRepository.save(consumer);
    }

    public INGLoanResponse getConsumerLoanInformation(Long customerId) {
        Optional<INGConsumerEntity> customerLoanInfo = consumerLoanRepository.findConsumerByConsumerId(customerId);
        if(customerLoanInfo.isEmpty()){
            throw new UsernameNotFoundException(
            		"The Consumer Loan Details not found by Consumer Id :"+customerId);
        }
        BigDecimal loanAmount= customerLoanInfo.get()
				        		.getLoanRequestDtos()
				        		.stream()
				        		.map(i -> i.getConsumerLoanAmount())
				        		.reduce(BigDecimal.ZERO, BigDecimal::add);
        return new INGLoanResponse(customerLoanInfo.get().getConsumerId(),
        		customerLoanInfo.get().getConsumerFullName(),loanAmount);
    }

    private INGConsumerEntity prepareConsumerLoanRequest(INGConsumerLoanRequest loanRequest) {
        INGConsumerEntity ingConsumerEntity = new INGConsumerEntity();
        ingConsumerEntity.setConsumerId(loanRequest.getConsumerId());
        ingConsumerEntity.setConsumerFullName(loanRequest.getConsumerFullName());
        List<INGConsumerLoanEntity> loanEntities =new ArrayList<>();
        INGConsumerLoanEntity loanEntity = new INGConsumerLoanEntity();
        loanEntity.setConsumerLoanAmount(loanRequest.getConsumerLoanAmount());
        loanEntities.add(loanEntity);
        ingConsumerEntity.setLoanRequestDtos(loanEntities);
        return ingConsumerEntity;
    }
}
