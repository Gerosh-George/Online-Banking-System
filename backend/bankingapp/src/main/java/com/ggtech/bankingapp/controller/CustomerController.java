package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.model.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.AuthenticationService;
import com.ggtech.bankingapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService custService;

	private ObjectMapper objectMapper;

	@Autowired
	AuthenticationService service;

	public CustomerController() {
		objectMapper=new ObjectMapper();
	}

	@PostMapping("/register")
	@CrossOrigin
	public ResponseEntity<LoginResponse> saveCustomer(@RequestBody @Valid Customer user) throws Exception{
		return ResponseEntity.ok(service.register(user));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> validateCustomer(@RequestBody LoginRequest u) throws Exception {
		return ResponseEntity.ok(service.authenticate(u));
	}

	@GetMapping("/customer/{cid}")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	public Customer getCustomer(@PathVariable("cid") long customerId) throws ResourceNotFoundException {
		return custService.getCustomer(customerId);
	}

	@PutMapping("/changePassword/{otp}")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	public String changePassword(@RequestBody LoginRequest u, @PathVariable("otp") String otp) {
		return custService.resetPassword(u, otp);
	}

	@PutMapping("/changeDetails")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	public String changeDetails(@Valid @RequestBody Customer u) {
		return custService.updateCustomerDetails(u);
	}

}
