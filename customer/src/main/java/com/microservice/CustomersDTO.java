package com.microservice;

public class CustomersDTO {
    public long document;
    public String firstName;
    public String lastName;
    public String gender;
    public String email;
    
    
    public CustomersDTO(long document, String firstName, String lastName, String gender, String email) {
        this.document = document;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
       
    }

}
