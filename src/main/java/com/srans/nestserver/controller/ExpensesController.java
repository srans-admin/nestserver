package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesController {
	Logger logger = LoggerFactory.getLogger(ExpensesController.class);
	@Autowired
	private ExpenseRepository expensesRepository;

	@Autowired
	private HostelRepository hostelRepository;

	@PostMapping("/expenses/{id}")
	public Expense createExpense(@PathVariable(value = "id") Long id, @Valid @RequestBody Expense expense) {
		logger.info("IN::POST::/expenses::saveExpense::" + expense);
		return hostelRepository.findById(id).map(hostel -> {
			expense.setHostel(hostel);

			logger.info("OUT::POST::/expenses::saveExpense::" + expense);
			return expensesRepository.save(expense);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}

	@GetMapping("/expenses")
	public List<Expense> getAllExpenses() {

		return expensesRepository.findAll();
	}

	@GetMapping("expenses/{id}")
	public List<Expense> getExpensesData(@PathVariable(value = "id") Long hostelId) {

		return expensesRepository.getExpenses(hostelId);
	}

	@PutMapping("/expenses/{id}")
	public ResponseEntity<Expense> updateExpenses(@PathVariable(value = "id") Long expensesId,
			@Valid @RequestBody Expense expensesDetails) throws ResourceNotFoundException {
		Expense expenses = expensesRepository.findById(expensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesId));

		expenses.setExpenseType(expensesDetails.getExpenseType());
		expenses.setAmount(expensesDetails.getAmount());
		expenses.setUpdatedAt(expensesDetails.getUpdatedAt());
		final Expense updatedExpenses = expensesRepository.save(expenses);
		return ResponseEntity.ok(updatedExpenses);
	}

	@DeleteMapping("/expenses/{id}")
	public Map<String, Boolean> deleteExpenses(@PathVariable(value = "id") Long expensesId)
			throws ResourceNotFoundException {
		Expense expenses = expensesRepository.findById(expensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesId));

		expensesRepository.delete(expenses);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
