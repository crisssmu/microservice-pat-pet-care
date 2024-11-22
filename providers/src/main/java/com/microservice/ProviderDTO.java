package com.microservice;

public class ProviderDTO {
    String name;
    String email;
    long document;
    long phoneNumber;
    String gender;
    RoleProvider role;

    
    public ProviderDTO() {
    }
    
    public ProviderDTO(String name, String email, long document, long phoneNumber, String gender, RoleProvider role) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getDocument() {
        return document;
    }
    public void setDocument(long document) {
        this.document = document;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public RoleProvider getRole() {
        return role;
    }
    public void setRole(RoleProvider role) {
        this.role = role;
    }
    

    
}
