package com.microservice;

public class Providers {
    private long id;
    private String name;
    private String email;
    private long document;
    private long phoneNumber;
    private String gender;
    private RoleProvider role;
    private float balance;
    private long idCompany;

    
    public Providers() {
    }

    
    public Providers(String name, String email, long document, long phoneNumber, String gender, RoleProvider role, float balance, long idCompany) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.balance = balance;
        this.idCompany = idCompany;
    }

    

    public Providers(long id, long document) {
        this.id = id;
        this.document = document;
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
    public long getIdCompany() {
        return idCompany;
    }
    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }
    public long getId() {
        return id;
    }


    public RoleProvider getRole() {
        return role;
    }


    public void setRole(RoleProvider role) {
        this.role = role;
    }


    public float getBalance() {
        return balance;
    }


    public void setBalance(float balance) {
        this.balance = balance;
    }

    
    
}
