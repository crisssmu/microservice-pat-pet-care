package com.microservice;

public class Veterinary extends TypeService{
    public Veterinary(float cost) {
        super(cost);
    }
    @Override
    public float paymentValue(float cost) {
        return cost*(0.1f+1);
    }

}
