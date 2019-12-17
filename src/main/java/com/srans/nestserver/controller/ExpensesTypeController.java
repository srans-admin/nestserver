package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.srans.nestserver.model.ExpensesType;
import com.srans.nestserver.repository.ExpenseTypeRepository;
import com.srans.nestserver.repository.HostelRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesTypeController {
	Logger logger = LoggerFactory.getLogger(ExpensesTypeController.class);
	@Autowired
	private ExpenseTypeRepository expensesTypeRepository;

	@Autowired
	private HostelRepository hostelRepository;

	@GetMapping("/expensestype")
	public List<ExpensesType> getAllExpensesType() {

		return expensesTypeRepository.findAll();
	}

	@PostMapping("/expensestype/{id}")
	public ExpensesType createExpenseType(@PathVariable(value = "id") Long id,
			@Valid @RequestBody ExpensesType expensestype) {
		return hostelRepository.findById(id).map(hostel -> {
			expensestype.setHostel(hostel);

			return expensesTypeRepository.save(expensestype);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}

	@PutMapping("/expensestype/{id}")
	public ResponseEntity<ExpensesType> updateExpensesType(@PathVariable(value = "id") Long ExpensesId,
			@Valid @RequestBody ExpensesType ExpensesDetails) throws ResourceNotFoundException {
		ExpensesType expensestype = expensesTypeRepository.findById(ExpensesId).orElseThrow(
				() -> new ResourceNotFoundException("ExpensesType not found for this id :: " + ExpensesId));

		expensestype.setExpenseType(ExpensesDetails.getExpenseType());
		final ExpensesType updatedExpensesType = expensesTypeRepository.save(expensestype);
		return ResponseEntity.ok(updatedExpensesType);
	}

	@DeleteMapping("/expensestype/{id}")
	public Map<String, Boolean> deleteExpensesType(@PathVariable(value = "id") Long ExpensesTypeId)
			throws ResourceNotFoundException {
		ExpensesType expensestype = expensesTypeRepository.findById(ExpensesTypeId).orElseThrow(
				() -> new ResourceNotFoundException("Expenses not found for this id :: " + ExpensesTypeId));

		expensesTypeRepository.delete(expensestype);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
