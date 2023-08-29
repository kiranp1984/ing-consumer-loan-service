package com.ing.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.loan.dto.request.INGConsumerLoanRequest;
import com.ing.loan.dto.response.INGLoanResponse;
import com.ing.loan.exception.UsernameNotFoundException;
import com.ing.loan.model.INGConsumerEntity;
import com.ing.loan.service.INGConsumerLoanService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class INGConsumerLoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private INGConsumerLoanService customerLoanService;

    public static String createLoanUrl = "/loan/save";

    public static String getLoanUrl = "/loan/";

    @Test
    void addCustomerLoanTestSuccess() throws Exception {
        INGConsumerEntity customer = INGConsumerEntity.builder().consumerId(101L).consumerFullName("Test").build();
        INGConsumerLoanRequest loanRequest = INGConsumerLoanRequest.builder().consumerId(101L).consumerFullName("Test").consumerLoanAmount(BigDecimal.valueOf(2000)).build();
        when(this.customerLoanService.saveConsumerLoan(Mockito.any())).thenReturn(customer);

        this.mockMvc.perform(post(createLoanUrl)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequest)))
                .andExpect(status().isCreated());

    }

    @Test
    void validationCheckExceptionaddCustomer() throws Exception {
        INGConsumerEntity customer = INGConsumerEntity.builder().consumerId(null).consumerFullName("Test").build();

        this.mockMvc.perform(post(createLoanUrl)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCustomerLoanAmountNotFoundSuccessful() throws Exception {
        long customerId = 101L;
        INGLoanResponse loanResponse = INGLoanResponse.builder().consumerId(101L).consumerFullName("Test").loanAmount(BigDecimal.valueOf(10000)).build();
        when(this.customerLoanService.getConsumerLoanInformation(customerId)).thenReturn(loanResponse);

        this.mockMvc.perform(get(getLoanUrl+customerId)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getCustomerLoanAmountNotFound() throws Exception {
        long customerId = 1L;
        when(this.customerLoanService.getConsumerLoanInformation(customerId)).thenThrow(new UsernameNotFoundException("Customer Loan Details not found by Customer Id :"+customerId));

        this.mockMvc.perform(get(getLoanUrl+customerId)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
