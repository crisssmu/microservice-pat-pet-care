package com.microservice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.Pet;
import com.microservice.Specie;
import com.microservice.database.Database;

@Component
public class PetRepository implements PetDAO {
    @Autowired
    private static Database db;

    public PetRepository() {
    }
    
    public PetRepository(Database db) {
        this.db = db;
    }

    @Override
    public void registerPet(Pet pet) {
        String query = "INSERT INTO pets(name, dateBirth, gender, specie, idOwner, document) VALUES(?,?,?,?,?)";
        try(PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)){
            pstmt.setString(1, pet.getName());
            pstmt.setDate(2, java.sql.Date.valueOf(pet.getDateBirth()));
            pstmt.setString(3, pet.getGender());
            pstmt.setString(4, pet.getSpecie().name());
            pstmt.setLong(5, pet.getIdCustomer());
            pstmt.setLong(6, pet.getDocument());
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT document, name, dateBirth, gender, specie, idOwner FROM pets";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Pet pet = new Pet(
                    rs.getLong("document"),
                    rs.getString("name"),
                    rs.getDate("dateBirth").toLocalDate(),
                    Specie.valueOf(rs.getString("specie")),
                    rs.getString("gender"),
                    rs.getLong(rs.getString("Customer"))
                );
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    @Override
    public long getPetDocumetByOwner(long documentCus, long documentPet) {
        String query = "SELECT idPet FROM pets WHERE idOwner  = ? AND document = ?";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setLong(1, documentCus);
            pstmt.setLong(2, documentPet);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("idPet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updatePet(Pet pet, long id) {
       String query = "UPDATE pets SET document = ?, name = ? dateBirth = ?, gender = ?, specie = ?, idOwner = ? WHERE idPet = ?";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setLong(1, pet.getDocument());
            pstmt.setString(2, pet.getName());
            pstmt.setDate(3, java.sql.Date.valueOf(pet.getDateBirth()));
            pstmt.setString(4, pet.getGender());
            pstmt.setString(5, pet.getSpecie().name());
            pstmt.setLong(6, pet.getIdCustomer());
            pstmt.setLong(7, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePet(long id) {
        String query = "DELETE FROM pets where idPet = ?";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
