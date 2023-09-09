package com.ggtech.bankingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue
	private long transactionId;
	private String transType;
	private double amount;
	
	private long accFrom;
	private long accTo;
	private String timestamp;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="accno")
	private Account acc_no;
	
}
