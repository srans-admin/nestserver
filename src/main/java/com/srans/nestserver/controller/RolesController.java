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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Role;
import com.srans.nestserver.repository.RoleRepository;

@CrossOrigin(origins = "*",allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1")
public class RolesController {
	Logger logger = LoggerFactory.getLogger(RolesController.class);
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/roles")
	public List<Role> getAllRoles() {
		logger.info("get all roles");
		return roleRepository.findAll();
	}
	

	@GetMapping("/roles/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId)


			throws ResourceNotFoundException {
		logger.info("IN::getRoleById::" + roleId);

		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
		logger.info("OUT::getRoleById::" + roleId);

		return ResponseEntity.ok().body(role);
	}

	@PostMapping("/roles")
	
	public Role createRole(@Valid @RequestBody Role role) {
		logger.info("IN::POST::/role::saveRole::" + role);

		role = roleRepository.save(role);
		logger.info("OUT::POST::/role::saveRole::" + role);
		  return role;
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable(value = "id") Long role_Id,
			@Valid @RequestBody Role rolesDetails) throws ResourceNotFoundException {
		logger.info("IN::POST::/role::updateRole::" + role_Id);

		Role role = roleRepository.findById(role_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " +role_Id));

		role.setRoleName(rolesDetails.getRoleName());
		role.setName(rolesDetails.getName());
		role.setEmailId(rolesDetails.getEmailId());
		final Role updatedRole = roleRepository.save(role);
		logger.info("OUT::POST::/role::updateRole::" + role_Id);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/roles/{id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "id") Long roleId)
			throws ResourceNotFoundException {
		logger.info("IN::POST::/role::deleteRole::" + roleId);

		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

		roleRepository.delete(role);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/role::deleteRole::" + roleId);
		return response;
	}
}
