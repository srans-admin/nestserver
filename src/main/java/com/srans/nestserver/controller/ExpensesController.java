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
import com.srans.nestserver.model.Expenses;
import com.srans.nestserver.repository.ExpensesRepository;






@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ExpensesController {
	//Logger logger = LoggerFactory.getLogger(ExpensesController.class);
	@Autowired
	private  ExpensesRepository  expensesRepository;
	
	@GetMapping("/expenses")
	public List<Expenses>getAllExpenses() {
		return expensesRepository.findAll();
	}

	@GetMapping("/expenses/{id}")
	public ResponseEntity<Expenses> getExpensesById(@PathVariable(value = "id") Long  ExpensesId)
			throws ResourceNotFoundException {
		 Expenses  expenses =  expensesRepository.findById(ExpensesId)
				.orElseThrow(() -> new ResourceNotFoundException(" Expenses not found for this id :: " + ExpensesId));
		return ResponseEntity.ok().body(expenses);
	}

	@PostMapping("/expenses")
	public  Expenses createExpenses(@Valid @RequestBody Expenses expenses) {
		return  expensesRepository.save(expenses);
	}

	@PutMapping("/expenses/{id}")
	public ResponseEntity<Expenses> updateExpenses(@PathVariable(value = "id") Long  ExpensesId,
			@Valid @RequestBody  Expenses  ExpensesDetails) throws ResourceNotFoundException {
		 Expenses  expenses =  expensesRepository.findById(ExpensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " +  ExpensesId));

		 expenses.setTypeOfExpenses(ExpensesDetails.getTypeOfExpenses());
		 expenses.setcost(ExpensesDetails.getcost());
		final  Expenses updatedExpenses = expensesRepository.save(expenses);
		return ResponseEntity.ok(updatedExpenses);
	}

	@DeleteMapping("/expenses/{id}")
	public Map<String, Boolean> deleteExpenses(@PathVariable(value = "id") Long ExpensesId)
			throws ResourceNotFoundException {
	Expenses expenses =  expensesRepository.findById(ExpensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + ExpensesId));

		 expensesRepository.delete(expenses);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
