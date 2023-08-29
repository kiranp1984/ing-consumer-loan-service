package com.ing.loan.dto.response;

import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class INGLoanResponse {

    private Long consumerId;
    private String consumerFullName;
    private BigDecimal loanAmount;
}
