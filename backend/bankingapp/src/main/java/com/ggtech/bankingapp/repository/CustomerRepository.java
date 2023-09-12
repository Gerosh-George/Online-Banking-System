package com.ggtech.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggtech.bankingapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	//Implement method to get ALl Users
}
