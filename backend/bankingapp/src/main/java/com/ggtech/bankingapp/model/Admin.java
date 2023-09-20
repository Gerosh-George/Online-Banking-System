package com.ggtech.bankingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Admin {
    @Id
    @Column(nullable=false)
    private String userid;

    @Column(nullable=false)
    @Length(min=8, max=20, message="Password must be between 8 to 20 characters")
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
