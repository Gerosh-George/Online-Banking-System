package com.ggtech.bankingapp.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Account {
	@Id
	private long accno;
	private String acctype;
	private double balance;
	private String openingDate;
	private String ifsc;
	private String branch;
	private boolean isDisabled;
	

	@ManyToOne
	@JoinColumn(name="userId")
	private Customer user;
	
	@OneToMany(mappedBy="acc_no", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Transaction> transaction;
	
	
	
}

