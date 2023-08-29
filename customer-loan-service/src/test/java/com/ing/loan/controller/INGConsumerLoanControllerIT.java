package com.ing.loan.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ing.loan.dto.request.INGConsumerLoanRequest;
import com.ing.loan.dto.response.INGLoanResponse;
import com.ing.loan.exception.ApiError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class INGConsumerLoanControllerIT {
    @LocalServerPort
    private  int port;

    private String baseUrl ="http://localhost";
    public static String createLoanUrl = "/save";
    public static String getLoanDetails = "/101";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    private void setUp() {
        baseUrl= baseUrl.concat(":").concat(port +"").concat("/loan"+"");

    }

    @Test
    void testSaveLoan(){
        baseUrl=baseUrl.concat(createLoanUrl);
        INGConsumerLoanRequest consumer = INGConsumerLoanRequest.builder().
        		consumerId(101L).consumerFullName("Test").consumerLoanAmount(BigDecimal.valueOf(2000)).build();
        String loanResponse = restTemplate.postForObject(baseUrl, consumer, String.class);
        Assertions.assertEquals("The Loan Created Successfully for the customer : "+consumer.getConsumerId(),loanResponse);
    }

    @Test
    void exceptionToTestSaveLoan(){
        baseUrl = baseUrl.concat(createLoanUrl);
        INGConsumerLoanRequest customer = INGConsumerLoanRequest.builder().consumerId(101L).consumerFullName("Test").consumerLoanAmount(BigDecimal.valueOf(200)).build();

        HttpClientErrorException.BadRequest thrown = Assertions.assertThrows(HttpClientErrorException.BadRequest.class, () -> {
        restTemplate.postForObject(baseUrl, customer, ApiError.class);
        });
    }

    @Test
    @Sql(statements = "insert into CONSUMER_DETAILS (consumer_id, consumer_full_name) values (11L,'Test')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "insert into loan_request (consumer_id,loan_amount,loan_id) values(11L,800,101)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGetLoan(){
        baseUrl=baseUrl.concat("/11");
        INGLoanResponse loanResponse = restTemplate.getForObject(baseUrl, INGLoanResponse.class);

        Assertions.assertEquals("Test",loanResponse.getConsumerFullName());
    }

    @Test
    void testExceptionGetLoanNotFound(){
        HttpClientErrorException.NotFound thrown = Assertions.assertThrows(HttpClientErrorException.NotFound.class,()->{
            baseUrl=baseUrl.concat("/13");
            restTemplate.getForObject(baseUrl, ApiError.class);
        });
    }
}
