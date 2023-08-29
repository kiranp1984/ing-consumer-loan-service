package com.ing.loan.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="CONSUMER_DETAILS")
public class INGConsumerEntity {

    @Id
    @Column(name = "consumer_id", nullable = false, updatable = false)
    private Long consumerId;

    @Column(name = "consumer_full_name", updatable = false)
    private String  consumerFullName;

    @OneToMany(targetEntity = INGConsumerLoanEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name="consumer_id", nullable = false, referencedColumnName = "consumer_id", updatable = false)
    private List<INGConsumerLoanEntity> loanRequestDtos;




}
