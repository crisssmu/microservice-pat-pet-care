package com.microservice.repository;

import java.util.List;

import com.microservice.Customer;


public interface CustomerDAO {
    void registerCustomer(Customer customer);
    List<Customer> getAllCustomers();
    long getCustomerByDocument(long document);
    void updateCustomer(Customer customer, long id);
    void deleteCustomer(long id);
    void changePassword(String email, String password);
    Customer findCustomerByEmail(String email);

}
