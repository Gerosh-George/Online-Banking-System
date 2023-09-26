package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService custService;

	@PostMapping("/register")
	@CrossOrigin
	public String saveCustomer(@RequestBody @Valid Customer user){
		return custService.saveCustomer(user);
	}

	@PostMapping("/login")
	public String validateCustomer(@RequestBody LoginRequest u) throws NoDataFoundException {
		return custService.validateCustomer(u);
	}

	@GetMapping("/customer/{cid}")
	public Customer getCustomer(@PathVariable("cid") long customerId) throws ResourceNotFoundException {
		return custService.getCustomer(customerId);
	}

	@PutMapping("/changePassword/{otp}")
	public String changePassword(@RequestBody LoginRequest u, @PathVariable("otp") String otp) throws ResourceNotFoundException {
		return custService.resetPassword(u, otp);
	}

	@PutMapping("/changeDetails")
	public String changeDetails(@Valid @RequestBody Customer u) {
		return custService.updateCustomerDetails(u);
	}

	@GetMapping("/allTransactions/{cid}")
	public List<Transaction> getAllTransactions(@PathVariable("cid") long customerId) throws ResourceNotFoundException {
		return custService.getAllUserTransactions(customerId);
	}
}
