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
import com.srans.nestserver.model.ExpensesCategory;
import com.srans.nestserver.repository.ExpenseCategoryRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesCategoryController {
	Logger logger = LoggerFactory.getLogger(ExpensesCategoryController.class);
	@Autowired
	private ExpenseCategoryRepository expensescategoryRepository;

	@PostMapping("/categories")
	@PreAuthorize("permitAll()")
	public ExpensesCategory createExpenseType(@Valid @RequestBody ExpensesCategory expensescategory) {
		logger.info("IN::POST::/expensescategory::saveExpenseCategory::" + expensescategory);

		expensescategory = expensescategoryRepository.save(expensescategory);

		logger.info("OUT::POST::/expensescategory::saveExpenseCategory::" + expensescategory);

		return expensescategory;

	}

	@GetMapping("/categories")
	@PreAuthorize("permitAll()")
	public List<ExpensesCategory> getAllExpenseCategory() {
		 logger.info("get all expenses category");

		return expensescategoryRepository.findAll();
	}

	@PutMapping("/categories/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<ExpensesCategory> updateExpensesCategory(@PathVariable(value = "id") Long expensescategoryId,
			@Valid @RequestBody ExpensesCategory expensescategoryDetails) throws ResourceNotFoundException {
		logger.info("IN::POST::/expensescategory::updateExpenseCategory::" + expensescategoryId);

		ExpensesCategory expensescategory = expensescategoryRepository.findById(expensescategoryId).
				orElseThrow(() -> new ResourceNotFoundException("ExpensesType not found for this id :: " + expensescategoryId));

		expensescategory.setDescription(expensescategoryDetails.getDescription());
		expensescategory.setTypeId(expensescategoryDetails.getTypeId());
		expensescategory.setExpenseType(expensescategoryDetails.getExpenseType());
	
		final ExpensesCategory updatedExpensesCategory = expensescategoryRepository.save(expensescategory);
		logger.info("OUT::POST::/expensescategory::updateExpenseCategory::" + expensescategoryId);
		return ResponseEntity.ok(updatedExpensesCategory);
	}

	@DeleteMapping("/categories/{id}")
	@PreAuthorize("permitAll()")
	public Map<String, Boolean> deleteExpensesType(@PathVariable(value = "id") Long expensescategoryId)
			throws ResourceNotFoundException {
		logger.info("IN::POST::/expensescategory::deleteExpenseCategory::" + expensescategoryId);

		ExpensesCategory expensescategory = expensescategoryRepository.findById(expensescategoryId).orElseThrow(
				() -> new ResourceNotFoundException("ExpensesCategory not found for this id :: " + expensescategoryId));

		expensescategoryRepository.delete(expensescategory);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/expensescategory::deleteExpenseCategory::" + expensescategoryId);

		return response;
	}
}
