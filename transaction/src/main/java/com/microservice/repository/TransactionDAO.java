package com.microservice.repository;

import java.util.List;

import com.microservice.Transaction;



public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    void payTransaction(long idTransaction, float amount);
    long getTransactionId(long idCustomer, long idService);
}
