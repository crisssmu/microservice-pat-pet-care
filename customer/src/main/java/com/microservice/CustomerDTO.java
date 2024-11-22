package com.microservice;
public class CustomerDTO {
    public long id;
    public long document;
    public String firstName;
    public String lastName;
    public String gender;
    public String email;
    public String password;
    
    
    public CustomerDTO() {
    }

    
    public CustomerDTO(long document, String firstName, String lastName, String gender, String email, String password) {
        this.document = document;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }


    
}
