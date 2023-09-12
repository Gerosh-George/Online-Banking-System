package com.ggtech.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService custService;
	
	@GetMapping("/customers")
	@CrossOrigin
	public List<Customer> getAllCustomers() {
		return custService.getAllCustomerList();
	}
	@PostMapping("/login")
	@CrossOrigin
	public String loginUser(@RequestBody Customer customer){
		return custService.validateCustomer(customer);
	}



	@PostMapping("/hello")
	@CrossOrigin
	public String ff() {
		return "Hello!";
	}
	
	@PostMapping("/saveCustomer")
	@CrossOrigin
	public String saveCustomer(@RequestBody Customer user) {
		return custService.saveCustomer(user);
	}
}
