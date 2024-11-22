package com.microservice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microservice.TypeService;
import com.microservice.TypeServiceFactory;
import com.microservice.database.Database;


@Component
public class TypeServiceRepository implements TypeServiceDAO {
    @Autowired
    private static Database db;


    public TypeServiceRepository() {
    }

    public TypeServiceRepository(Database db) {
        this.db = db;
    }

    public void registerTypeService(TypeService typeService) {
        String query = "INSERT INTO typeServices(name, cost) VALUES(?,?)";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setString(1, typeService.getClass().getSimpleName());
            pstmt.setFloat(2, typeService.getCost());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TypeService> getAllTypeServices() {
        List<TypeService> typeServices = new ArrayList<>();
        String query = "SELECT name, cost FROM typeServices";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("name");
                float cost = rs.getFloat("cost");
                TypeService typeService = TypeServiceFactory.createTypeService(type, cost);
                typeServices.add(typeService);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeServices;
    }

    @Override
    public long getTypeServiceByCode(String name) {
        String query = "SELECT idTypeService FROM typeServices WHERE name = ?";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("idTypeService");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateTypeService(TypeService typeService, long id) {
       String query = "UPDATE typeServices SET name = ?, cost = ? WHERE idTypeService = ?";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setString(1, typeService.getClass().getSimpleName());
            pstmt.setFloat(2, typeService.getCost());
            pstmt.setLong(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTypeService(long id) {
        String query = "DELETE FROM typeServices WHERE idTypeService = ?";
        try (PreparedStatement pstmt = Database.getInstance(db).prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
