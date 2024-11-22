package com.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.microservice.MapperPet;
import com.microservice.Pet;
import com.microservice.PetDTO;
import com.microservice.repository.PetRepository;

@Service
public class PetService implements InPetService {
    @Autowired
    PetRepository pr;

    @Override
    public void registerPet(PetDTO petDTO) {
        if (petDTO != null) {
            MapperPet mapper = new MapperPet();
            Pet pet = mapper.toEntity(petDTO);
            pr.registerPet(pet);
        }
    }

    @Override
    public List<Pet> getAllPets() {
        return pr.getAllPets();
    }

    @Override
    public long getPetByDocument(long document, long documentPet) {
        return pr.getPetDocumetByOwner(document, documentPet);
    }

    @Override
    public void updatePet(Pet pet, long id) {
        pr.updatePet(pet, id);
    }

    @Override
    public void deletePet(long id) {
        pr.deletePet(id);
    }

}
