package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Expenses;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long>{

}
