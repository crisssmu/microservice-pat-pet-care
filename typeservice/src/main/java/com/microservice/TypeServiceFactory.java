package com.microservice;

public class TypeServiceFactory {

    public static TypeService createTypeService(String typeService, float cost) {
    switch (typeService) {
        case "Care":
            return new Care(cost);
        case "Shower":
            return new Shower(cost);
        case "Veterinary":
            return new Veterinary(cost);
        default:
        throw new IllegalArgumentException("Invalid type service" + typeService);
           
    }
    }
}
