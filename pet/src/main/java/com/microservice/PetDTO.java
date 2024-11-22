package com.microservice;

import java.time.LocalDate;

public class PetDTO {
    String name;
    LocalDate dateBirth;
    String gender;
    Specie specie;
    long idCustomer;
    long document;

    public PetDTO() {
    }

    public PetDTO(String name, LocalDate dateBirth, String gender, Specie specie, long idCustomer, long document) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.specie = specie;
        this.idCustomer = idCustomer;
        this.document = document;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    


}
