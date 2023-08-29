package com.ing.loan.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ing.loan.model.INGConsumerEntity;
import com.ing.loan.model.INGConsumerLoanEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class INGConsumerLoanRepositoryTest {

    @Autowired
    private INGConsumerLoanRepository consumerLoanRepositoryTest;

    @AfterEach
    void tearDown() {
        consumerLoanRepositoryTest.deleteAll();
    }

    @Test
    void checkIfSaveLoanSucessful() {
        List<INGConsumerLoanEntity> loanRequestDtos = new ArrayList<>();
        INGConsumerLoanEntity loanRequestDto =new INGConsumerLoanEntity(1L, BigDecimal.valueOf(600));
        loanRequestDtos.add(loanRequestDto);
        INGConsumerEntity customerDto = new INGConsumerEntity(Long.valueOf(11),"Kiran",loanRequestDtos);
        INGConsumerEntity customerLoanData = consumerLoanRepositoryTest.save(customerDto);
        assertEquals(11L, customerLoanData.getConsumerId());
    }

    @Test
    void checkIfLoanExistsByCustomerId() {
    	List<INGConsumerLoanEntity> loanRequestDtos = new ArrayList<>();
        INGConsumerLoanEntity loanRequestDto =new INGConsumerLoanEntity(1L, BigDecimal.valueOf(600));
        loanRequestDtos.add(loanRequestDto);
        INGConsumerEntity customerDto = new INGConsumerEntity(Long.valueOf(11),"Kiran",loanRequestDtos);
        consumerLoanRepositoryTest.save(customerDto);
        boolean existingCustomer = consumerLoanRepositoryTest.existsById(customerDto.getConsumerId());

        assertTrue(existingCustomer);
    }

    @Test
    void checkIfLoanDoesNotExistsByCustomerId() {
        boolean existingCustomer = consumerLoanRepositoryTest.existsById(Long.valueOf(101));
        assertFalse(existingCustomer);

    }

}