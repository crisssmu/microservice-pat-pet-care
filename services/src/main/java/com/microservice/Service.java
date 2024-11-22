package com.microservice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Service {
    long id;
    Date dateService;
    long idPet;
    long idTransaction;
    long idTypeService;
    State state;
    long idProvider;
    long idCustomer;
    LocalDate dateCite; 
    LocalTime time;
    

    public Service() {
    }




    public Service(Date dateService, long idPet, long idTransaction, long idTypeService, State state, long idProvider,
            long idCustomer, LocalDate dateCite, LocalTime time) {
        this.dateService = dateService;
        this.idPet = idPet;
        this.idTransaction = idTransaction;
        this.idTypeService = idTypeService;
        this.state = state;
        this.idProvider = idProvider;
        this.idCustomer = idCustomer;
        this.dateCite = dateCite;
        this.time = time;
    }




    public LocalDate getDateCite() {
        return dateCite;
    }


    public void setDateCite(LocalDate dateCite) {
        this.dateCite = dateCite;
    }



    public Date getDateService() {
        return dateService;
    }

    public long getIdPet() {
        return idPet;
    }

    public long getIdTypeService() {
        return idTypeService;
    }

   
    public long getId() {
        return id;
    }

    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }


    public State getState() {
        return state;
    }


    public void setState(State state) {
        this.state = state;
    }

    public void setIdPet(long idPet) {
        this.idPet = idPet;
    }

    public void setIdTypeService(long idTypeService) {
        this.idTypeService = idTypeService;
    }

    public long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(long idProvider) {
        this.idProvider = idProvider;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(long idTransaction) {
        this.idTransaction = idTransaction;
    }




    public LocalTime getTime() {
        return time;
    }




    public void setTime(LocalTime time) {
        this.time = time;
    }

    
    
}
