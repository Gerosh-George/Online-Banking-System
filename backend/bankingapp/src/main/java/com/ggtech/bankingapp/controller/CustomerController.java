package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService custService;

	@PostMapping("/register")
	@CrossOrigin
	public String saveCustomer(@RequestBody Customer user) {
		return custService.saveCustomer(user);
	}

	@PostMapping("/login")
	public String validateCustomer(@RequestBody LoginRequest u) {
		return custService.validateCustomer(u);
	}
}
