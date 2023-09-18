package com.ggtech.bankingapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.ggtech.bankingapp.model.Transaction;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("select transaction from Transaction transaction where (transaction.accFrom=?1 or transaction.accTo=?1)")
	public List<Transaction> findByAccountNumber(long accno);

	/*@Query("select transaction from Transaction transaction where (transaction.timestamp>=?1 and transaction.timetamp<=?2 and transaction.accFrom=?3 or transaction.accTo=?3)")
	public List<Transaction> accountStatement(Date fromDate, Date toDate, long accno);*/
}
