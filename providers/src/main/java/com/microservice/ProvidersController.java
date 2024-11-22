package com.microservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.services.ProvidersService;

@RestController
@RequestMapping("api/providers")
public class ProvidersController {

    @Autowired
    private ProvidersService providerService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> registerProvider(@RequestBody ProviderDTO providerDTO) {
        long idProvider = providerService.getProviderByDocument(providerDTO.document);
        HashMap<String, Object> response = new HashMap<>();
        if (idProvider != -1) {
            providerService.registerProvider(providerDTO);
            response.put("message", "Provider registered successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Provider already exists");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/allPets")
    public ResponseEntity<HashMap<String, Object>> getAllProviders() {
        if(providerService.getAllProviders().isEmpty()){
            HashMap<String, Object> response = new HashMap<>();
            response.put("providers", providerService.getAllProviders());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<HashMap<String, Object>> updateProvider(@RequestBody Providers provider, long document) {
        long idProvider = providerService.getProviderByDocument(document);
        if(idProvider != -1){
            providerService.updateProvider(provider, idProvider);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Provider updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> deleteProvider(long document) {
        long idProvider = providerService.getProviderByDocument(document);
        if(idProvider != -1){
            providerService.deleteProvider(idProvider);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Provider deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
