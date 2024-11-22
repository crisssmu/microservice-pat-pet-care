package com.microservice;

public class Shower extends TypeService{
    public Shower(float cost) {
        super(cost);
    }
    @Override
    public float paymentValue(float cost) {
        return cost*(0.1f+1);
    }

}
