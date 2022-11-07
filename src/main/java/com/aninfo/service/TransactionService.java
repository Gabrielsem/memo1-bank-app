package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction Transaction) {
        return transactionRepository.save(Transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Collection<Transaction> getTransactionsByAccount(Long cbu) {
        return transactionRepository.findAll().stream()
        .filter(t -> t.getAccount().getCbu().equals(cbu))
        .collect(Collectors.toList());
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void save(Transaction Transaction) {
        transactionRepository.save(Transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Transactional
    public Transaction withdraw(Account account, Double sum) {

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        
        Transaction transaction = new Transaction(sum, account, false);

        transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public Transaction deposit(Account account, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Transaction transaction = new Transaction(sum, account, true);

        transactionRepository.save(transaction);

        return transaction;
    }

}
