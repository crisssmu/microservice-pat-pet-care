package com.microservice;

public class CustomerMapper {

    
    public static CustomerDTO toDTO(Customer entity) {
        return new CustomerDTO(entity.getDocument(), entity.getFirstName(), entity.getLastName(), entity.getGender(), entity.getEmail(), entity.getPassword());
    }

    
    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setDocument(dto.document);
        customer.setFirstName(dto.firstName);
        customer.setLastName(dto.lastName);
        customer.setGender(dto.gender);
        customer.setEmail(dto.email);
        customer.setPassword(dto.password);
        return customer;
    }

    public static CustomersDTO CustomerstoDTO(Customer entity) {
        return new CustomersDTO(entity.getDocument(), entity.getFirstName(), entity.getLastName(), entity.getGender(), entity.getEmail());
    }

}
