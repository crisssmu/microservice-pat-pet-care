package com.microservice;

import java.time.LocalDate;



public class Pet {
    
    private long id;
    private String name;
    LocalDate dateBirth;
    Specie specie;
    long idCustomer;
    String gender;
    long document;


    
    public Pet() {
    }
    public Pet(String name, LocalDate dateBirth, Specie specie, String gender, long idCustomer) {
        this.document = PetDocumentGenerator.generator(specie);
        this.name = name;
        this.dateBirth = dateBirth;
        this.specie = specie;
        this.gender = gender;
        this.idCustomer = idCustomer;
    }


    public Pet(long document, String name, LocalDate dateBirth, Specie specie, String gender, long idCustomer) {
        this.document = document;
        this.name = name;
        this.dateBirth = dateBirth;
        this.specie = specie;
        this.gender = gender;
        this.idCustomer = idCustomer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDateBirth() {
        return dateBirth;
    }
    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }
    public Specie getSpecie() {
        return specie;
    }
    public void setSpecie(Specie specie) {
        this.specie = specie;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public long getDocument() {
        return document;
    }
    public void setDocument(long document) {
        this.document = document;
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
    
    
    
}
