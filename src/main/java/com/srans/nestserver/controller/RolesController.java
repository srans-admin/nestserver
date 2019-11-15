package com.srans.nestserver.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.srans.nestserver.model.Role;
import com.srans.nestserver.repository.RoleRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class RolesController {
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/roles")
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Role role = roleRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(role);
	}

	@PostMapping("/roles")
	public Role createRole(@Valid @RequestBody Role role) {
		return roleRepository.save(role);
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Role employeeDetails) throws ResourceNotFoundException {
		Role role = roleRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + employeeId));

		role.setEmailId(employeeDetails.getEmailId());
		role.setLastName(employeeDetails.getLastName());
		role.setFirstName(employeeDetails.getFirstName());
		final Role updatedRole = roleRepository.save(role);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/roles/{id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Role role = roleRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + employeeId));

		roleRepository.delete(role);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
