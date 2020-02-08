package com.srans.nestserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	@Query(value = "SELECT * FROM expense WHERE hostel_id=?1", nativeQuery = true)
	public List<Expense> getExpenses(Long hostelId);

	
	  @Query(value ="SELECT * FROM expense WHERE hostel_id=?1",nativeQuery = true)
		public List<Expense> getExpenses(String hostelName);

	@Query(value = "SELECT * FROM expensescategory WHERE type_id=?1", nativeQuery = true)
	public List<Expense> getExpenses(long type_id);

	@Query(value = "SELECT * FROM expense WHERE hostel_id=?1", nativeQuery = true)
	public Optional<Expense> getExpensesOfParticularHostel(Long hostelId);

	@Query(value = "SELECT * FROM expense WHERE admin_id=?1", nativeQuery = true)
	public List<Expense> getExpensesOfParticularAdmin(Long adminId);
	
	@Query(value="SELECT e FROM Expense e WHERE hostelId=?1")
	public List<Expense> getexpenseHistoryDetail(Long hostelid);
	
	@Query(value = "SELECT count(e.hostelId) from Expense e where hostelId=?1")
	public Long checkHostelId(Long hostelId);

}
