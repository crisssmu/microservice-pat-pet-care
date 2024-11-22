package com.microservice;

public class ServiceMapper {
    public static Service mapToService(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setState(serviceDTO.getState());
        service.setIdPet(serviceDTO.getIdPet());
        service.setIdTypeService(serviceDTO.getIdTypeService());
        return service;
    }

    public static ServiceDTO mapToServiceDTO(Service service) {
        return new ServiceDTO(service.getState(), service.getIdPet(), service.getIdTypeService());
    }
}
