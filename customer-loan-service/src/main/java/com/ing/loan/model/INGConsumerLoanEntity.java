package com.ing.loan.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="LOAN_REQUEST")
public class INGConsumerLoanEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOAN")
    @SequenceGenerator(name = "SEQ_LOAN", allocationSize = 1)
    @Column(name = "LOAN_ID")
    private Long consumerLoanId;

    @Column(name = "loan_amount", nullable = false)
    private BigDecimal consumerLoanAmount;

}
