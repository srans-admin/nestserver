package com.srans.nestserver.controller;

import java.util.List;

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
import com.srans.nestserver.model.Rooms;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.RoomRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class RoomController {

	@Autowired
	private FloorRepository floorRepository;


	

}
