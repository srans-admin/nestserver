package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
