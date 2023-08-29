package com.ing.loan.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class INGConsumerLoanRequest {

    @NotNull
    @Min(value = 1)
    private Long consumerId;

    @NotBlank
    private String  consumerFullName;

    @NotNull
    @Min(value = 500)
    @DecimalMax(value = "12000.50")
    private BigDecimal consumerLoanAmount;
}
