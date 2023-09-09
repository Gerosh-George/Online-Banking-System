package com.ggtech.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.CustomerService;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	CustomerService custService;
	
	@GetMapping("/helo")
	public String f() {
		return "Hello!";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@RequestBody Customer user) {
		return custService.saveCustomer(user);
	}
}
