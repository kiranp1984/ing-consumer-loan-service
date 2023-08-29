package com.ing.loan.service;

import com.ing.loan.dto.request.INGConsumerLoanRequest;
import com.ing.loan.dto.response.INGLoanResponse;
import com.ing.loan.model.INGConsumerEntity;


public interface INGConsumerLoanService {

    
    public INGConsumerEntity saveConsumerLoan(INGConsumerLoanRequest loanRequest);
    
    public INGLoanResponse getConsumerLoanInformation(Long customerId);
}
