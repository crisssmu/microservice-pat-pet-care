package com.microservice.service;

import java.util.List;

import com.microservice.Transaction;

public interface ITransactionService {
    void registerTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    void payTransaction(long idTransaction, float amount);
    long getTransactionId(long idCustomer, long idService);
}
