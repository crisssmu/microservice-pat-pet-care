package com.microservice.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.microservice.Transaction;
import com.microservice.database.Database;


public class TransactionRepository implements TransactionDAO {
private final Database db;
    private Connection connection;



    public TransactionRepository(Database db) {
        this.db = db;
    }


    @Override
    public void addTransaction(Transaction transaction) {
        String query = "INSERT INTO transactions(idCustomer, dateTranst, amount, idService) VALUES(?,?,?,?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, transaction.getIdCustomer());
            pstmt.setDate(2, (Date) transaction.getDateTransaction());
            pstmt.setFloat(3, transaction.getAmount());
            pstmt.setLong(4, transaction.getIdService());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT idCustomer, dateTranst, amount, idService FROM transactions";
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeQuery();
            while(pstmt.getResultSet().next()) {
                Transaction transaction = new Transaction();
                transaction.setIdCustomer(pstmt.getResultSet().getLong("idCustomer"));
                transaction.setDateTransaction(pstmt.getResultSet().getDate("dateTranst"));
                transaction.setAmount(pstmt.getResultSet().getFloat("amount"));
                transaction.setIdService(pstmt.getResultSet().getLong("idService"));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public long getTransactionId(long idCustomer, long idService) {
        String query = "SELECT idTransaction FROM transactions WHERE idCustomer = ? AND idService = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, idCustomer);
            pstmt.setLong(2, idService);
            pstmt.executeQuery();
            while(pstmt.getResultSet().next()) {
                return pstmt.getResultSet().getLong("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Override
    public void payTransaction(long idTransaction, float amount) {
        String query = "UPDATE transactions SET amount = ? WHERE idTransaction = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setFloat(1, amount);
            pstmt.setLong(2, idTransaction);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
