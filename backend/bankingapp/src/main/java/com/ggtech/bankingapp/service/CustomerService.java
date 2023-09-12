package com.ggtech.bankingapp.service;

import java.util.List;
import java.util.Optional;

import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggtech.bankingapp.model.Customer;

import com.ggtech.bankingapp.repository.CustomerRepository;



@Service
public class CustomerService {
	@Autowired
	CustomerRepository custRepo;



	public List<Customer> getAllCustomerList(){

        return custRepo.findAll();

	}

	@Autowired
	AccountRepository accRepo;

	public String saveCustomer(Customer cust) {
		String result = "";
		Optional<Customer> o = custRepo.findById(cust.getCustomerId());
		if (o.isPresent()) {
			result = "Customer already exists!";
		} else {
			result = "Customer created successfully!";
			Customer obj = custRepo.save(cust);
		}
		return result;
	}

	public String validateCustomer(LoginRequest u) {
		Customer cust = null;
		String result = "";

		Optional<Customer> obj = custRepo.findById(u.getCustomerId());

		if (obj.isPresent()) {
			cust = obj.get();
		}
		if (cust == null) {
			result = "Invalid Customer";
		} else {
			if (u.getPassword().equals(cust.getPassword())) {
				result = "Login success";
			} else {
				result = "Login failed";
			}
		}
		return result;
	}
}
