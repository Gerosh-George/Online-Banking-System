package com.ggtech.bankingapp.service;

import java.util.List;
import java.util.Optional;

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


	public String validateCustomer(Customer cust) {
		String result;
		Optional<Customer> o = custRepo.findById((cust.getCustomerId()));

		if(o.isPresent()){
			//Create a JWT Token
			result = "Login Successful";
		}
		else{
			result = "Login Failed";
		}
		return result;
	}
}
