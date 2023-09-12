package com.ggtech.bankingapp.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Account {

	@Id
	@Column(name="Account_no")
	private long accountNo;

	@Column(nullable=false)
	private String accountType;

	@Column(nullable=false)
	private double balance;

	@Column(nullable=false)
	private String branch;

	@Column(nullable=false)
	private String ifsc;

	@Column(nullable=false)
	private boolean isdisabled;

	@Column()
	private LocalDateTime openeingDate;

	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;



	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public boolean isDisabled() {
		return isdisabled;
	}

	public void setDisabled(boolean isdisabled) {
		this.isdisabled = isdisabled;
	}

	public LocalDateTime getOpeneingDate() {
		return openeingDate;
	}

	public void setOpeneingDate(LocalDateTime openeingDate) {
		this.openeingDate = openeingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




}