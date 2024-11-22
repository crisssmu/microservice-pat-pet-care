package com.microservice.repository;



import java.util.List;

import com.microservice.Service;
import com.microservice.State;




public interface ServiceDAO {
    void registerService(Service service);
    List<Service> getAllServices();
    long findServiceByStatePetType(Service service);
    void updateServiceForTransaction(long id, long idTransaction);
    void deleteService(long id);
    long findTransactionByService(long id);
    void updateStateService(long id, State state);
    
}
