package com.ggtech.bankingapp.repository;

import com.ggtech.bankingapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    public Admin findByUserid(String userId);
}
