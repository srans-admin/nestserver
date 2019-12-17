package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.ExpensesType;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpensesType, Long> {
	
	

}
