package com.ggtech.bankingapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggtech.bankingapp.model.Customer;

import com.ggtech.bankingapp.repository.CustomerRepository;



@Service
public class CustomerService {
	@Autowired
	CustomerRepository custRepo;

	
	public String saveCustomer(Customer cust) {
		
		String result = "";
		Optional<Customer> o = custRepo.findById(cust.getUserId());
		if (o.isPresent()) {
			result = "Customer already exists!";
		} else {
			result = "Customer created successfully!";
			Customer obj = custRepo.save(cust);
		}
		return result;
	}
}
