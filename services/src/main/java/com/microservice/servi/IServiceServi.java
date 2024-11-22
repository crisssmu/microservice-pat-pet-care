package com.microservice.servi;

import java.util.List;

import com.microservice.Service;
import com.microservice.ServiceDTO;
import com.microservice.State;




public interface IServiceServi {
    void registerService(Service service);
    List<Service> getAllServices();
    long findServiceByStatePetType(ServiceDTO serviceDTO);
    void updateServiceForTransaction(long id, long idTransaction);
    long findTransactionByService(long id);
    void deleteService(long id);
    void updateStateService(long id, State state);

}
