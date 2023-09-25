package com.ggtech.bankingapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	@Column(nullable=false)
	@Length(min=1,message="Name can't be empty")
	private String name;
	
	@Column(nullable=false)
	@Length(min=8, max=20, message="Password must be between 8 to 20 characters")
	private String password;

	@Pattern(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(nullable=false)
	private String email;

	@Column(nullable=false)
	private long mobile;
	
	@Pattern(message = "aadhar is not valid", regexp = "\\d{12}")
	@Column(nullable=false)
	private String aadhar;
	
	@Pattern(message = "dob is not valid", regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
	@Column(nullable=false)
	private String dob;

	private String address;
	
	private String fathername;

	private String mothername;
	
	
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public long getMobile() {
		return mobile;
	}



	public void setMobile(long mobile) {
		this.mobile = mobile;
	}



	public String getAadhar() {
		return aadhar;
	}



	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getFathername() {
		return fathername;
	}



	public void setFathername(String fathername) {
		this.fathername = fathername;
	}



	public String getMothername() {
		return mothername;
	}



	public void setMothername(String mothername) {
		this.mothername = mothername;
	}



	public List<Account> getAccount() {
		return account;
	}



	public void setAccount(List<Account> account) {
		this.account = account;
	}



	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<Account> account;
	
	
	
}
	
	

