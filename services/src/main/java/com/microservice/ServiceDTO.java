package com.microservice;

public class ServiceDTO {
    
    public State state;
    public long idPet;
    public long idTypeService;

    
    public ServiceDTO() {
    }
    
    public ServiceDTO(State state, long idPet, long idTypeService) {
        this.state = state;
        this.idPet = idPet;
        this.idTypeService = idTypeService;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public long getIdPet() {
        return idPet;
    }
    public void setIdPet(long idPet) {
        this.idPet = idPet;
    }
    public long getIdTypeService() {
        return idTypeService;
    }
    public void setIdTypeService(long idTypeService) {
        this.idTypeService = idTypeService;
    }

    
    
    
}
