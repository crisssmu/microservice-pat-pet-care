package com.microservice.repository;

import java.util.List;

import com.microservice.Pet;

public interface PetDAO {
    void registerPet(Pet pet);
    List<Pet> getAllPets();
    long getPetDocumetByOwner(long documentCus, long documentPet);
    void updatePet(Pet pet, long id);
    void deletePet(long id);
}
