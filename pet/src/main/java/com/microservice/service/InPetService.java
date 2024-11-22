package  com.microservice.service;

import java.util.List;

import com.microservice.Pet;
import com.microservice.PetDTO;


public interface InPetService {
    void registerPet(PetDTO petDTO);
    List<Pet> getAllPets();
    long getPetByDocument(long documentCus, long documentPet);
    void updatePet(Pet pet, long id);
    void deletePet(long id);
}
