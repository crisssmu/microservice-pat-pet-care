package com.microservice.servicess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.Customer;
import com.microservice.CustomerDTO;
import com.microservice.CustomerMapper;
import com.microservice.CustomersDTO;
import com.microservice.repository.CustomerRepositoryPostgres;


@Service
public class CustomerService implements InCustomerService {

    @Autowired
    CustomerRepositoryPostgres cr;

    
    CustomerDTO customerDTO = new CustomerDTO();

    public CustomerService(CustomerRepositoryPostgres cr) {
        this.cr = cr;
    }

    @Override
    public void registerCustomer(CustomerDTO customer) {
        try {
            Customer cus = CustomerMapper.toEntity(customer);
            cr.registerCustomer(cus);
            System.out.println("Cliente registrado");
        } catch (Exception e) {
            System.out.println("No se pudo registrar el cliente");
        }
    }

    @Override
    public long getCustomerByDocument(long document) {
        return cr.getCustomerByDocument(document);
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        long idCustomer = cr.getCustomerByDocument(customer.document);
        if(idCustomer == -1){
            System.out.println("Cliente no encontrado");
        } else{
            Customer cus = CustomerMapper.toEntity(customer);
        cr.updateCustomer(cus, idCustomer);
        System.out.println("Cliente actualizado");
        }
        
    }

    @Override
    public void deleteCustomer(long id) {
        cr.deleteCustomer(id);
    }

    @Override
    public List<CustomersDTO> getAllCustomers() {
        List<Customer> customers = cr.getAllCustomers();
        List<CustomersDTO> customersDTO = new ArrayList<>();
        for (Customer customer : customers) {
            CustomersDTO customerssDTO = CustomerMapper.CustomerstoDTO(customer);
            customersDTO.add(customerssDTO);
        }
        return customersDTO;

    }

    @Override
    public void changePassword(String email, String password) {
        cr.changePassword(email, password);
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        Customer customer = cr.findCustomerByEmail(email);
        System.out.println("Buscando cliente");
        try {
            if(customer != null){
                System.out.println("Cliente encontrado");
                return CustomerMapper.toDTO(customer);
            }
            return null;
        } catch (Exception e) {
            System.out.println("No se encontro el cliente");
            return null;
        }
    }
    
}
