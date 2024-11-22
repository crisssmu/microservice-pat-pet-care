package com.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.Transaction;
import com.microservice.repository.TransactionRepository;

@Service
public class TransactionService implements ITransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void registerTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    @Override
    public long getTransactionId(long idCustomer, long idService) {
        return transactionRepository.getTransactionId(idCustomer, idService);
    }

    @Override
    public void payTransaction(long idTransaction, float amount) {
        if(amount > 0) {
            transactionRepository.payTransaction(idTransaction, amount);
        }
    }
}
