package com.microservice.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microservice.Providers;
import com.microservice.RoleProvider;
import com.microservice.database.Database;



public class ProviderRepository implements ProviderDAO {
    private Database db;
    private Connection connection;

    public ProviderRepository() {
    }

    public ProviderRepository(Database db) {
        this.db = db;
    }

    @Override
    public void registerProvider(Providers provider) {
        String query = "INSERT INTO providers(name, email, document, phoneNumber, gender, idCompany, rol,balance) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, provider.getName());
            pstmt.setString(2, provider.getEmail());
            pstmt.setLong(3, provider.getDocument());
            pstmt.setLong(4, provider.getPhoneNumber());
            pstmt.setString(5, provider.getGender());
            pstmt.setLong(6, provider.getIdCompany());
            pstmt.setString(7, provider.getRole().name());
            pstmt.setFloat(8, provider.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Providers> getProviders() {
        List<Providers> providers = new ArrayList<>();
        String query = "SELECT name, email, document, phoneNumber, gender, idCompany, rol, balance FROM providers";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Providers provider = new Providers(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("document"),
                    rs.getLong("phoneNumber"),
                    rs.getString("gender"),
                    RoleProvider.valueOf(rs.getString("rol")),
                    rs.getFloat("balance"),
                    rs.getLong("idCompany")
                );
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }

    @Override
    public long getProviderById(long document) {
        String query = "SELECT idProvider FROM providers WHERE document = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, document);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("idProvider");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateProvider(Providers provider, long id) {
        String query = "UPDATE providers SET name = ?, email = ?, email = ?, phoneNumber = ?. gender = ?, idCompany = ?, rol = ? WHERE idProvider = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, provider.getName());
            pstmt.setString(2, provider.getEmail());
            pstmt.setLong(3, provider.getDocument());
            pstmt.setLong(4, provider.getPhoneNumber());
            pstmt.setString(5, provider.getGender());
            pstmt.setLong(6, provider.getIdCompany());
            pstmt.setString(7, provider.getRole().name());
            pstmt.setLong(8, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProvider(long id) {
        String query = "DELETE FROM providers WHERE idProvider = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void TopUpMoney(long id, float money) {
        String query = "UPDATE providers SET balance = ? WHERE idProvider = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setFloat(1, money);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
