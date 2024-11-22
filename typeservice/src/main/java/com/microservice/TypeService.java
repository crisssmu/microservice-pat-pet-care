package com.microservice;

public abstract class TypeService {
    private long id;
    private float cost;

    public TypeService() {
    }

    public TypeService(float cost) {
      
        this.cost = cost;
    }

    public TypeService(long id) {
        this.id = id;
    }

    abstract public float paymentValue(float cost);

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    
}
