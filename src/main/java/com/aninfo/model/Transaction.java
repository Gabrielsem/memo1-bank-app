package com.aninfo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
    
    private Boolean isDeposit;
    
    private Double amount;

    private LocalDateTime date;

    public Transaction(){
    }

    public Transaction(Double amount, Account account, Boolean isDeposit) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.account = account;
        this.isDeposit = isDeposit;

        if (isDeposit) {
            // if amount >= 2000 then 10% bonus, but not more than 500
            if (amount >= 2000) {
                Double extra = amount * 0.1;
                System.out.println("Amount: " + amount);
                System.out.println("Extra: " + extra);
                this.account.setBalance(this.account.getBalance() + amount + (extra > 500 ? 500 : extra));
            } else {
                this.account.setBalance(this.account.getBalance() + amount);
            }
        } else {
            account.setBalance(account.getBalance() - amount);
        }
    }

    public Long getid() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public Boolean getIsDeposit() {
        return isDeposit;
    }
}
