package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Expense;
import com.srans.nestserver.repository.ExpenseRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.TenantRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesController {
	// Logger logger = LoggerFactory.getLogger(ExpensesController.class);
	@Autowired
	private ExpenseRepository expensesRepository;

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private TenantRepository tenantRepository;

	@GetMapping("/expenses")
	public List<Expense> getAllExpenses() {

		return expensesRepository.findAll();
	}

	/*
	 * @GetMapping("/expenses/{id}") public ResponseEntity<Expense>
	 * getExpensesById(@PathVariable(value = "id") Long ExpensesId) throws
	 * ResourceNotFoundException { Expense expenses =
	 * expensesRepository.findById(ExpensesId) .orElseThrow(() -> new
	 * ResourceNotFoundException(" Expenses not found for this id :: " +
	 * ExpensesId)); return ResponseEntity.ok().body(expenses); }
	 */

	@GetMapping("expenses/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();
	}

	@GetMapping("expenses/{id}")
	public List<Object[]> getExpensesData(@PathVariable(value = "id") Long id) {

		return expensesRepository.getExpenses(id);
	}

	@PostMapping("/expenses/{id}")
	public Expense createExpense(@PathVariable(value = "id") Long id, @Valid @RequestBody Expense expense) {
		return hostelRepository.findById(id).map(hostel -> {
			expense.setHostel(hostel);

			return expensesRepository.save(expense);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}

	@PutMapping("/expenses/{id}")
	public ResponseEntity<Expense> updateExpenses(@PathVariable(value = "id") Long ExpensesId,
			@Valid @RequestBody Expense ExpensesDetails) throws ResourceNotFoundException {
		Expense expenses = expensesRepository.findById(ExpensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + ExpensesId));

		expenses.setExpenseType(ExpensesDetails.getExpenseType());
		expenses.setAmount(ExpensesDetails.getAmount());
		expenses.setUpdatedAt(ExpensesDetails.getUpdatedAt());
		final Expense updatedExpenses = expensesRepository.save(expenses);
		return ResponseEntity.ok(updatedExpenses);
	}

	@DeleteMapping("/expenses/{id}")
	public Map<String, Boolean> deleteExpenses(@PathVariable(value = "id") Long ExpensesId)
			throws ResourceNotFoundException {
		Expense expenses = expensesRepository.findById(ExpensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + ExpensesId));

		expensesRepository.delete(expenses);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
