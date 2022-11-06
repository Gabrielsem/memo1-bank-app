package com.aninfo.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    private Collection<Transaction> transactions;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        this.setBalance(this.getBalance() + transaction.getAmount());
    }

}
