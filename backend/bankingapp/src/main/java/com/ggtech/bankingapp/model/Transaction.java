package com.ggtech.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import  javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.constraints.NotEmpty;



@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;

	@NotEmpty(message = "transType cannot be empty")
	private String transType;

	@NotEmpty(message = "amount cannot be empty")
	private double amount;
	
	private long accFrom;

	@NotEmpty(message = "accTo cannot be empty")
	private long accTo;
	private String timestamp;
	private String status;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="acc_no")
	private Account acc_no;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getAccFrom() {
		return accFrom;
	}

	public void setAccFrom(long accFrom) {
		this.accFrom = accFrom;
	}

	public long getAccTo() {
		return accTo;
	}

	public void setAccTo(long accTo) {
		this.accTo = accTo;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Account getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(Account acc_no) {
		this.acc_no = acc_no;
	}
	
	
	
}
