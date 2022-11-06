package com.aninfo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;

    private LocalDateTime date;

    public Transaction(){
    }

    public Transaction(Double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
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

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
