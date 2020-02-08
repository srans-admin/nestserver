
package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Expense;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Room;


import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.ExpenseRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSConstants;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")

public class ExpenseController {
	Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	@Autowired
	private ExpenseRepository expensesRepository;
	
	@Autowired
	private HostelRepository hostelRepo;
	


	@Autowired
	private UserRepository userRepo;

	@PostMapping("/expenses")
	@PreAuthorize("permitAll()")
	public Expense createExpense(@Valid @RequestBody Expense expense) {
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

	@GetMapping("expenses/hostelname/{name}")

	@PreAuthorize("permitAll()")
	public List<Expense> getExpensesData(@PathVariable(value = "name") String hostelName) {

		List<Expense> expenses = expensesRepository.getExpenses(hostelName);
		return expenses;
	}
	/*
	 * @GetMapping("expenses/adminid/{id}")
	 * 
	 * @PreAuthorize("permitAll()") public List<Expense>
	 * getExpenseData(@PathVariable(value = "id") Long adminId) {
	 * 
	 * List<Expense> expenses =
	 * expensesRepository.getExpensesOfParticularAdmin(adminId); return expenses; }
	 */

	@GetMapping("/expenses/expensehistory")

	@PreAuthorize("permitAll()")
	List<Expense> getexpenseHistoryDetail( @RequestParam("adminId") Long adminId)
			throws ResourceNotFoundException {

		logger.info("IN::getexpenseHistoryDetail::" + adminId );

		List<Expense> expenseInfo = new ArrayList<>();

				
			Long[] hostelId = HostelRepository.getAdminHostelId(adminId);
			for (Long hostelInfo : hostelId) {
				expensesRepository.getexpenseHistoryDetail(hostelInfo).stream().forEach(expenseDetails -> {
					Expense expense = new Expense();
					expense.setExpenseType(expenseDetails.getExpenseType());
					expense.setAmount(expenseDetails.getAmount());
					expense.setCreatedAt(expenseDetails.getCreatedAt());
					expense.setHostelId(hostelInfo);
					expense.setHostelName(hostelRepo.getOne(hostelInfo).getHostelName());
					expenseInfo.add(expense);
				});
			}
		

		logger.info("OUT::getexpenseHistoryDetail::" + adminId);
		return (expenseInfo);
	}
	
	
	
	
	
	

	/*
	 * @GetMapping("/expenses/hostelid/{id}/adminid/{admin_id}")
	 * 
	 * @PreAuthorize("permitAll()") public ResponseEntity<Room>
	 * getHostelById(@PathVariable(value = "id") Long hostel_id,
	 * 
	 * @PathVariable(value = "admin_id") Long admin_id) { if
	 * (!hostelRepo.existsById(admin_id)) { throw new
	 * ResourceNotFoundException("HostelId " + hostel_id + " not found"); }
	 * logger.info("IN::getFloorById::" + hostel_id);
	 * logger.info("OUT::getFloorById::" + hostel_id);
	 * 
	 * Hostel hostel = hostelRepo.findById(admin_id).orElseThrow(() -> new
	 * ResourceNotFoundException( "Admin not found for this Adminid :: " + hostel_id
	 * + "Admin not found for this Admin id::" + admin_id)); return
	 * ResponseEntity.ok().body(hostel); }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/expenses/{hostel_id}")
	public Optional<Expense> getExpensesOfParticularHostel(@PathVariable(value = "id") long hostel_id) {
		return expensesRepository.findById(hostel_id);
	}

	@GetMapping("/expenses/{admin_id}")
	public Optional<Expense> getExpensesOfParticularAdmin(@PathVariable(value = "id") long adminId) {
		return expensesRepository.findById(adminId);
	}


	/*/@GetMapping("expenses/{adminId}")
	
	//@GetMapping("/expenses/expensehistory")
	//@PreAuthorize("permitAll()") 
	///List<Expense> getexpenseHistoryDetail(@RequestParam("id") Long idType , @RequestParam("adminId") Long adminId) throws ResourceNotFoundException {
		
		//logger.info("IN::getexpenseHistoryDetail::" + idType+"::"+adminId);
		
	//	List<Expense> expenseInfo = new ArrayList<>();
	//
		//Long checkHostel=expensesRepository.checkHostelId(idType);
	//	System.out.println(checkHostel);
	//	//User user=userRepo.getOne(idType);
		/if(idType!=0&&adminId==0) {
		expensesRepository.getexpenseHistoryDetail(idType).stream().forEach(expenseDetails -> {
			 Expense expense = new Expense();
			expense.setExpenseType(expenseDetails.getExpenseType());
			expense.setAmount(expenseDetails.getAmount());
			expense.setCreatedAt(expenseDetails.getCreatedAt());
			expenseInfo.add(expense);
		  });
		}
		
		else {
			
			Long [] hostelId=hostelRepo.getAdminHostelId(adminId);
			for(Long hostelInfo:hostelId) {
				expensesRepository.getexpenseHistoryDetail(hostelInfo).stream().forEach(expenseDetails -> {
					 Expense expense = new Expense();
					expense.setExpenseType(expenseDetails.getExpenseType());
					expense.setAmount(expenseDetails.getAmount());
					expense.setCreatedAt(expenseDetails.getCreatedAt());
					expenseInfo.add(expense);
				  });
			}
		}
		
		logger.info("OUT::getexpenseHistoryDetail::" + idType+"::"+adminId);
		return (expenseInfo);
	}
	*//
	@PutMapping("/expenses/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Expense> updateExpenses(@PathVariable(value = "id") Long expensesId,
			@Valid @RequestBody Expense expensesDetails) throws ResourceNotFoundException {
		logger.info("IN::POST::/expenses::updateExpense::" + expensesId);

		Expense expenses = expensesRepository.findById(expensesId)
				.orElseThrow(() -> new ResourceNotFoundException("Expenses not found for this id :: " + expensesId));

		expenses.setExpenseType(expensesDetails.getExpenseType());
		expenses.setAmount(expensesDetails.getAmount());
		// expenses.setHostelName(expensesDetails.getHostelName());
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
