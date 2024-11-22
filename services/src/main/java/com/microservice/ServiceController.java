package com.microservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.microservice.servi.ServiceServi;

@RestController
@RequestMapping("api/service")
public class ServiceController {
    @Autowired
    private ServiceServi sr;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> registerService(@RequestBody Service service) {
        ServiceDTO serviceDTO = new ServiceDTO(service.getState(), service.getIdPet(), service.getIdTypeService());
        long id = sr.findServiceByStatePetType(serviceDTO);
        HashMap<String, Object> response = new HashMap<String, Object>();
        if(id == -1){
            
            sr.registerService(service);
            response.put("Service", service);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else{
            response.put("Service", "Servicio ya registrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> deleteService(@RequestBody Service service) {
        ServiceDTO serviceDTO = new ServiceDTO(service.getState(), service.getIdPet(), service.getIdTypeService());
        long id = sr.findServiceByStatePetType(serviceDTO);
        HashMap<String, Object> response = new HashMap<String, Object>();
        if(id != -1){
            long idTransaction = sr.findTransactionByService(id);
            if(idTransaction != -1){
            response.put("Service", "Servicio eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("Service", "Esta servicio no se puede eliminar");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.put("Service", "Servicio no encontrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
    } 

    @PostMapping("/updateState")
    public ResponseEntity<HashMap<String, Object>> updateStateService(@RequestBody Service service, @RequestBody State state) {
        ServiceDTO serviceDTO = new ServiceDTO(service.getState(), service.getIdPet(), service.getIdTypeService());
        long id = sr.findServiceByStatePetType(serviceDTO);
        HashMap<String, Object> response = new HashMap<String, Object>();
        if(id != -1){
            sr.updateStateService(id, state);
            response.put("Service", "Servicio actualizado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Service", "Servicio no encontrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping("/updateTransaction")
    public ResponseEntity<HashMap<String, Object>> updateServiceForTransaction(@RequestBody Service service, @RequestBody long idTransaction) {
        ServiceDTO serviceDTO = new ServiceDTO(service.getState(), service.getIdPet(), service.getIdTypeService());
        long id = sr.findServiceByStatePetType(serviceDTO);
        HashMap<String, Object> response = new HashMap<String, Object>();
        if(id != -1){
            sr.updateServiceForTransaction(id, idTransaction);
            response.put("Service", "Servicio actualizado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Service", "Servicio no encontrado");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping("/getAll")
    public ResponseEntity<HashMap<String, Object>> getAllServices() {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("Services", sr.getAllServices());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
