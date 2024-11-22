package com.microservice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.service.PetService;

@RestController
@RequestMapping("api/pet")
public class PetController {

    @Autowired
    private PetService petService;

    
    @PostMapping("/register/{document}")
    public ResponseEntity<HashMap<String, Object>> registerPet(@PathVariable ("document") long document, @RequestBody PetDTO petDTO) {
        String url = "http://localhost:8081/api/customer/id/"+document;
        long idCustomer = 0;
         try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            idCustomer = Long.parseLong(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> response = new HashMap<>();
        if(idCustomer != 0) {
            petDTO.idCustomer = idCustomer;
            petService.registerPet(petDTO);
            response.put("pet", petDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        // Add more data to the response as needed

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        
    }

    @PostMapping("/allPets")
    public ResponseEntity<HashMap<String, Object>> getAllPets() {
        if(petService.getAllPets().isEmpty()) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", "Pets not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } 
        HashMap<String, Object> response = new HashMap<>();
        response.put("pets", petService.getAllPets());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{document},{documentPet}")
    public ResponseEntity<HashMap<String, Object>> getPetByDocument(@RequestBody long document, @RequestBody long documentPet) {
        long idPet = petService.getPetByDocument(document, documentPet);
        HashMap<String, Object> response = new HashMap<>();
        if(idPet != -1) {
            response.put("idPet", idPet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Pet not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<HashMap<String, Object>> updatePet(@RequestBody Pet pet, @RequestBody long documentCus,@RequestBody long document) {
        long idPet = petService.getPetByDocument(documentCus, document);
        if(idPet == -1) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", "Pet not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        HashMap<String, Object> response = new HashMap<>();
        petService.updatePet(pet, idPet);
        response.put("pet", "Pet updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> deletePet(@RequestBody long documentCus, @RequestBody long documentPet) {
        long idPet = petService.getPetByDocument(documentCus, documentPet);
        if(idPet == -1) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", "Pet not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        HashMap<String, Object> response = new HashMap<>();
        petService.deletePet(idPet);
        response.put("pet", "Pet deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

}
