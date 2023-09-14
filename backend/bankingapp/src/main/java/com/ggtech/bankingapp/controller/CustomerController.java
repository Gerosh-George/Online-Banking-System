package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.CustomerService;
import com.ggtech.bankingapp.util.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService custService;

	private ObjectMapper objectMapper;

	public CustomerController() {
		objectMapper=new ObjectMapper();
	}

	@PostMapping("/register")
	@CrossOrigin
	public String saveCustomer(@RequestBody JsonNode jsonNode) throws JsonProcessingException, IllegalArgumentException  {
		
		String[] fields={"name","password","mobile","email","aadhar","dob"};
		if(!Validator.nonNullFieldsValidator(jsonNode, fields)){
			return "Fields can't be Empty";
		}
		try {
			if(!Validator.dateFormatValidator(jsonNode.get("dob"))){
				return "Incorrect Dob Format";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Incorrect date";
		}
		Customer user = objectMapper.treeToValue(jsonNode, Customer.class);

		String name=jsonNode.get("name").asText(),
				password=jsonNode.get("password").asText(),
				email=jsonNode.get("email").asText(),
				aadhar=jsonNode.get("aadhar").asText(),
				dob=jsonNode.get("dob").asText();
		Long mobile=jsonNode.get("name").asLong();

		String address="",fathername="",mothername="";
		if(jsonNode.has("address"))
		address=jsonNode.get("address").asText();

		if(jsonNode.has("address"))
		address=jsonNode.get("address").asText();

		if(jsonNode.has("address"))
		address=jsonNode.get("address").asText();
		user.setName(name);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setAadhar(aadhar);
		user.setDob(dob);
		user.setAddress(address);
		user.setFathername(fathername);
		user.setMothername(mothername);

		return custService.saveCustomer(user);
	}

	@PostMapping("/login")
	public String validateCustomer(@RequestBody LoginRequest u) {
		return custService.validateCustomer(u);
	}
}
