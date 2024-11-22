package com.microservice;

import java.util.Date;

public class Transaction {
    long id;
    long idCustomer;
    Date dateTransaction;
    long idService;
    float amount;

    public Transaction() {
    }

    public Transaction(long idCustomer, Date dateTransaction, float amount, long idService) {
        this.idCustomer = idCustomer;
        this.dateTransaction = dateTransaction;
        this.amount = amount;
        this.idService = idService;
  
    }

    public long getId() {
        return id;
    }
    
    public long getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }
    public Date getDateTransaction() {
        return dateTransaction;
    }
    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
    public long getIdService() {
        return idService;
    }
    public void setIdService(long idService) {
        this.idService = idService;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    
}
