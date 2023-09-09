package com.ggtech.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ggtech.bankingapp.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	@Query("select account from Account account where account.user.userId=?1")
	public List<Account> findByUsername(String username);
}
