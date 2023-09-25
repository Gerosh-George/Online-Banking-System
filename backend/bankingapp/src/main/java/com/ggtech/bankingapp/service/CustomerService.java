package com.ggtech.bankingapp.service;

import java.util.*;
import java.util.stream.Collectors;

import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;
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

	@Autowired
	TransactionRepository tranRepo;

	public String saveCustomer(Customer cust) {
		String result = "";
		Customer o = custRepo.findByAadhar(cust.getAadhar());
		if (o!=null) {
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

	public Customer getCustomer(long cid) throws ResourceNotFoundException{
		Customer obj = custRepo.findById(cid).orElse(null);

		if(obj==null){
			Account accObj = accRepo.findById(cid).orElse(null);
			if(accObj==null) {
				throw new ResourceNotFoundException("Customer with this id does not exist");
			}
			obj = accObj.getCustomer();
		}

		return obj;
	}

	public String resetPassword(LoginRequest u, String otp) {
		String result = "";

		Customer cust = custRepo.findById(u.getCustomerId()).orElse(null);

		if (cust == null)
			result = "Invalid customer";
		else {
			if (otp.equals("101010")) {
				cust.setPassword(u.getPassword());
				custRepo.save(cust);
				result = "Success!";
			} else
				result = "Invalid OTP";
		}
		return result;
	}

	public String updateCustomerDetails(Customer u) {
		Customer cust = custRepo.findById(u.getCustomerId()).orElse(null);
		String result="";
		if (cust == null)
			result = "Invalid Customer";
		else {
			cust.setFathername(u.getFathername());
			cust.setMothername(u.getMothername());
			cust.setAddress(u.getAddress());
			custRepo.save(cust);
			result = "Success!";
		}
		return result;
	}

	public List<Transaction> getAllUserTransactions(long cid) throws ResourceNotFoundException {
		Customer obj = custRepo.findById(cid).orElse(null);
		if(obj==null){
			throw new ResourceNotFoundException("User with this id does not exist");
		}

		List<Account> accountList = obj.getAccount();
		List<Transaction> transactionList = new ArrayList<>();
		for(Account acc : accountList){
			transactionList.addAll(tranRepo.findByAccountNumber(acc.getAccountNo()));
		}

        Set<Transaction> set = new LinkedHashSet<>(transactionList);
		transactionList.clear();
		transactionList.addAll(set);

		return transactionList.stream().sorted(Comparator.comparing(Transaction::getTimestamp).reversed()).limit(10).collect(Collectors.toList());

	}

}
