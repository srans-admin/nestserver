
package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpenseController {
	Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	@Autowired
	private ExpenseRepository expensesRepository;

	@PostMapping("/expenses")
	@PreAuthorize("permitAll()")
	public Expense createExpense( @Valid @RequestBody Expense expense) {
		logger.info("IN::POST::/expenses::saveExpense::" + expense);
		
		expense = expensesRepository.save(expense);
		
		logger.info("OUT::POST::/expenses::saveExpense::" + expense);
		return expense;
	}

	@GetMapping("/expenses")
	@PreAuthorize("permitAll()")
	public List<Expense> getAllExpenses() {
		  logger.info("get all expenses");
		return expensesRepository.findAll();
	}

	@GetMapping("expenses/{id}")
	@PreAuthorize("permitAll()")
	public List<Expense> getExpensesData(@PathVariable(value = "id") Long hostelId) {
		logger.info("IN::getExpensesData::" + hostelId);

		List<Expense> expenses = expensesRepository.getExpenses(hostelId);
		logger.info("OUT::getExpensesData::" + hostelId);
		return expenses;
	}
	
	@PutMapping("/expenses/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Expense> updateExpenses(@PathVariable(value = "id") Long expensesId,
			@Valid @RequestBody Expense expensesDetails) throws ResourceNotFoundException {
		logger.info("IN::POST::/expenses::updateExpense::" + expensesId);

		Expense expenses = expensesRepository.findById(expensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesId));

		expenses.setExpenseType(expensesDetails.getExpenseType());
		expenses.setAmount(expensesDetails.getAmount());
		//expenses.setHostelName(expensesDetails.getHostelName());
		final Expense updatedExpenses = expensesRepository.save(expenses);
		logger.info("OUT::POST::/expenses::updateExpense::" + expensesId);

		return ResponseEntity.ok(updatedExpenses);
	}

	@DeleteMapping("/expenses/{id}")
	@PreAuthorize("permitAll()")
	public Map<String, Boolean> deleteExpenses(@PathVariable(value = "id") Long expensesId)
			throws ResourceNotFoundException {
		logger.info("IN::POST::/expenses::deleteExpense::" + expensesId);

		Expense expenses = expensesRepository.findById(expensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesId));

		expensesRepository.delete(expenses);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/expenses::deleteExpense::" + expensesId);

		return response;
	}
}
