package com.ggtech.bankingapp.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class AddBalanceRequest {

    @Column(nullable=false)
    @NotNull(message = "accno can't be Empty")
    private long accno;

    @Column(nullable=false)
    @NotNull(message = "amount can't be Empty")
    private double amount;

    public long getAccno() {
        return accno;
    }

    public void setAccno(long accno) {
        this.accno = accno;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
