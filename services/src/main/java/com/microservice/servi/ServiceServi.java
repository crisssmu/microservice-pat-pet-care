package com.microservice.servi;

import java.util.List;

import com.microservice.Service;
import com.microservice.ServiceDTO;
import com.microservice.ServiceMapper;
import com.microservice.State;
import com.microservice.repository.ServiceRepository;



@org.springframework.stereotype.Service
public class ServiceServi implements IServiceServi {
    ServiceRepository sr = new ServiceRepository();

    @Override
    public void registerService(Service service) {
        sr.registerService(service);
    }

    @Override
    public List<Service> getAllServices() {
        return sr.getAllServices();
    }

    @Override
    public void updateServiceForTransaction(long id, long idTransaction) {
        if(id != -1){
            sr.updateServiceForTransaction(id,idTransaction);
        }
    }
    @Override
    public void deleteService(long id) {
        sr.deleteService(id);
    }


    @Override
    public long findServiceByStatePetType(ServiceDTO serviceDTO) {
        if(serviceDTO != null){
            Service service = ServiceMapper.mapToService(serviceDTO);
            return sr.findServiceByStatePetType(service);
        }
        else{
            System.out.println("Error: Service no existe");
            return -1;
        }
    }

    @Override
    public long findTransactionByService(long id) {
        if(id != -1){
            System.out.println("Transaccion encontrada");
            return sr.findTransactionByService(id);
        } else {
            System.out.println("Error: Transaction no existe");
            return -1;
        }
    }

    @Override
    public void updateStateService(long id, State state) {
       if(id != -1){
           sr.updateStateService(id, state);
            System.out.println("Servicio actualizado");
       } else {
              System.out.println("Error: Service no existe");
       }
    }


}
