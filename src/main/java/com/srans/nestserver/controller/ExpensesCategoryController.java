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
import com.srans.nestserver.model.ExpensesCategory;
import com.srans.nestserver.repository.ExpenseCategoryRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesCategoryController {
	Logger logger = LoggerFactory.getLogger(ExpensesCategoryController.class);
	@Autowired
	private ExpenseCategoryRepository expensesTypeRepository;

	

	@PostMapping("/category")
	public ExpensesCategory createExpenseType(@Valid @RequestBody ExpensesCategory expensescategory) {
		
		return expensesTypeRepository.save(expensescategory);
	}

	@GetMapping("/category")
	public List<ExpensesCategory> getAllExpensesType() {

		return expensesTypeRepository.findAll();
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<ExpensesCategory> updateExpensesType(@PathVariable(value = "id") Long expensesId,
			@Valid @RequestBody ExpensesCategory ExpensesDetails) throws ResourceNotFoundException {
		ExpensesCategory expensestype = expensesTypeRepository.findById(expensesId).orElseThrow(
				() -> new ResourceNotFoundException("ExpensesType not found for this id :: " + expensesId));

		expensestype.setExpenseType(ExpensesDetails.getExpenseType());
		expensestype.setUpdatedAt(ExpensesDetails.getUpdatedAt());
		final ExpensesCategory updatedExpensesType = expensesTypeRepository.save(expensestype);
		return ResponseEntity.ok(updatedExpensesType);
	}

	@DeleteMapping("/category/{id}")
	public Map<String, Boolean> deleteExpensesType(@PathVariable(value = "id") Long expensesTypeId)
			throws ResourceNotFoundException {
		ExpensesCategory expensestype = expensesTypeRepository.findById(expensesTypeId).orElseThrow(
				() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesTypeId));

		expensesTypeRepository.delete(expensestype);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
