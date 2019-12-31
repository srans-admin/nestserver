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
import com.srans.nestserver.model.ExpensesType;
import com.srans.nestserver.repository.ExpenseCategoryRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesCategoryController {
	Logger logger = LoggerFactory.getLogger(ExpensesCategoryController.class);
	@Autowired
	private ExpenseCategoryRepository expensesTypeRepository;

	

	@PostMapping("/categories")
	@PreAuthorize("permitAll()")
	public ExpensesType createExpenseType(@Valid @RequestBody ExpensesType expensescategory) {
		
		return expensesTypeRepository.save(expensescategory);
	}

	@GetMapping("/categories")
	@PreAuthorize("permitAll()")
	public List<ExpensesType> getAllExpensesType() {

		return expensesTypeRepository.findAll();
	}
	
	@PutMapping("/categories/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<ExpensesType> updateExpensesType(@PathVariable(value = "id") Long expensesId,
			@Valid @RequestBody ExpensesType expensesDetails) throws ResourceNotFoundException {
		ExpensesType expensestype = expensesTypeRepository.findById(expensesId).orElseThrow(
				() -> new ResourceNotFoundException("ExpensesType not found for this id :: " + expensesId));

		expensestype.setName(expensesDetails.getName());
		expensestype.setDesc(expensesDetails.getDesc());
		expensestype.setUpdatedAt(expensesDetails.getUpdatedAt());
		final ExpensesType updatedExpensesType = expensesTypeRepository.save(expensestype);
		return ResponseEntity.ok(updatedExpensesType);
	}

	@DeleteMapping("/categories/{id}")
	@PreAuthorize("permitAll()")
	public Map<String, Boolean> deleteExpensesType(@PathVariable(value = "id") Long expensesTypeId)
			throws ResourceNotFoundException {
		ExpensesType expensestype = expensesTypeRepository.findById(expensesTypeId).orElseThrow(
				() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesTypeId));

		expensesTypeRepository.delete(expensestype);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
