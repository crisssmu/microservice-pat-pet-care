package com.microservice.service;

import java.util.List;

import com.microservice.TypeService;


public interface InTypeService {
    void registerTypeService(TypeService typeService);
    List<TypeService> getAllTypeServices();
    long getTypeServiceByCode(String name);
    void updateTypeService(TypeService typeService, long id);
    void deleteTypeService(long id);
}
