package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.ExpensesCategory;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpensesCategory, Long> {
	
	

}
