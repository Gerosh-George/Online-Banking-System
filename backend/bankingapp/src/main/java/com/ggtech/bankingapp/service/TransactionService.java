package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.exceptions.BalanceInsufficientException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transRepo;

    @Autowired
    AccountRepository accRepo;

    public Transaction transact(Transaction trans) throws Exception {
        if(!trans.getTransType().equalsIgnoreCase("Withdraw") && trans.getAccFrom()==trans.getAccTo()){
            throw new BalanceInsufficientException("Transferring funds to same account number not allowed");
        }
        long accnumber = trans.getAccFrom();
        long toAccNum = trans.getAccTo();
        Account acc = accRepo.findById(accnumber).orElse(null);
        try {
		        if(acc==null) {
		        	throw new BalanceInsufficientException(accnumber + " account doesn't exits");
		        }    
		
		        if(accRepo.findById(toAccNum).isEmpty()){
		        	throw new BalanceInsufficientException(toAccNum + " account doesn't exits");
		        }
		        
		        if(acc.isDisabled()) {
		        	throw new BalanceInsufficientException(accnumber + " account is disabled");
		        }
		        
		        if(accRepo.findById(toAccNum).get().isDisabled()) {
		        	throw new BalanceInsufficientException(toAccNum + " account is disabled");
		        }
		        
		        double balance = acc.getBalance();
		        double amt = trans.getAmount();

				if(amt<=0){
					throw new BalanceInsufficientException("Amount can't be <= 0");
				}

		        if(amt>balance) {
		        	throw new BalanceInsufficientException("Amount is not sufficient");
		        }
		        
		        else {
					balance -= amt;
					if(trans.getTransType().equalsIgnoreCase("Withdraw")){
						trans.setAccTo(0L);
                    } else {
						Account acc2 = accRepo.findById(toAccNum).get();
						if (accnumber != toAccNum) {
							double balance2 = acc2.getBalance();
							balance2 += amt;
							acc2.setBalance(balance2);
							accRepo.save(acc2);
						}
                    }
                    acc.setBalance(balance);
                    accRepo.save(acc);
                }
		        trans.setAcc_no(acc);
		        Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
		        trans.setTimestamp(timeStamp);
		        trans.setStatus("SUCCESS");
		        return transRepo.save(trans);
        }catch(BalanceInsufficientException ex) {

			if(trans.getTransType().equalsIgnoreCase("Withdraw")){
				trans.setAccTo(0L);
			}

        	trans.setAcc_no(acc);
	        Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
	        trans.setTimestamp(timeStamp);
	        trans.setStatus("FAIL");
	        transRepo.save(trans);
        	throw new BalanceInsufficientException(ex.getMessage());
        }
    }


    public List<Transaction> accountStatement(Date startDate, Date endDate, long accno) throws ParseException {
       List<Transaction> transactionList= transRepo.findByAccountNumber(accno);

       List<Transaction> statement =new ArrayList<>();
       if(endDate.after(startDate)) {
           for (Transaction transaction : transactionList) {
               if (transaction.getTimestamp() == startDate || transaction.getTimestamp() == endDate || (transaction.getTimestamp().after(startDate) && transaction.getTimestamp().before(endDate))) {
                   statement.add(transaction);
               }
           }
       }
       return statement;
    }

    public List<Transaction> accountSummary(long accno)
    {
        List<Transaction> transactionList= transRepo.findByAccountNumber(accno);
        return transactionList.stream().sorted(Comparator.comparing(Transaction::getTimestamp).reversed()).limit(5).collect(Collectors.toList());
    }

    //TODO
    public String withdrawal(){
        return null;
    }

}
