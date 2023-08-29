package com.ing.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.loan.model.INGConsumerEntity;

import java.util.Optional;

@Repository
public interface INGConsumerLoanRepository extends JpaRepository<INGConsumerEntity, Long> {
	Optional<INGConsumerEntity> findConsumerByConsumerId(Long customerId);
}
