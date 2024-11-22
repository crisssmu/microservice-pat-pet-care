package com.microservice.servicess;

import java.util.List;

import com.microservice.Customer;
import com.microservice.CustomerDTO;
import com.microservice.CustomersDTO;




public interface InCustomerService {
    
    void registerCustomer(CustomerDTO customer);
    List<CustomersDTO> getAllCustomers();
    long getCustomerByDocument(long document);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(long id);
    void changePassword(String email, String password);
    CustomerDTO findCustomerByEmail(String email);
}
