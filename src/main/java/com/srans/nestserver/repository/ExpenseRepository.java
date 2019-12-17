package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	@Query(value="SELECT type_of_expenses,ammount FROM expense WHERE hostel_id=?1", nativeQuery = true)
	public List<Object[]> getExpenses(Long id);

}
