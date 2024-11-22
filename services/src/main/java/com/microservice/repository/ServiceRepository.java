package com.microservice.repository;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.microservice.Service;
import com.microservice.State;
import com.microservice.database.Database;





public class ServiceRepository implements ServiceDAO {
    private static Database db;
    private Connection connection;

    public ServiceRepository() {
    }
    
    public ServiceRepository(Database db) {
        this.db = db;

    }

    @Override
    public void registerService(Service service) {
        String query = "INSERT INTO services(dateservice, idpet, idtransaction, idtypeservice, state, idprovider, idcustomer, datecite, time) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setDate(1, (Date)service.getDateService());
            pstmt.setLong(2, service.getIdPet());
            pstmt.setLong(3, service.getIdTransaction());
            pstmt.setLong(4, service.getIdTypeService());
            pstmt.setString(5, service.getState().name());
            pstmt.setLong(6, service.getIdProvider());
            pstmt.setLong(7, service.getIdCustomer());
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String query ="SELECT idpet, idcustomer, idprovider, idtypeservice, dateservice, idtransaction, state, datecite, time, state FROM services";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Service service = new Service();
                service.setIdPet(rs.getLong("idpet"));
                service.setIdCustomer(rs.getLong("idcustomer"));
                service.setIdProvider(rs.getLong("idprovider"));
                service.setIdTypeService(rs.getLong("idtypeservice"));
                service.setDateService(rs.getDate("dateservice"));
                service.setIdTransaction(rs.getLong("idtransaction"));
                service.setState(State.valueOf(rs.getString("state")));
                service.setDateCite(rs.getDate("datecite").toLocalDate());
                service.setTime(rs.getTime("time").toLocalTime());
                services.add(service);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return services;
    }


    @Override
    public void updateServiceForTransaction(long id, long idTransaction) {
        String query = "UPDATE services idtransaction = ?  WHERE id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setLong(1, id);
            pstmt.setLong(2, idTransaction);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    @Override
    public void deleteService(long id) {
        String query = "DELETE FROM services WHERE id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public long findServiceByStatePetType(Service service) {
        String query = "SELECT id FROM services WHERE state = ? AND idpet = ? AND idtypeservice = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, service.getState().name());
            pstmt.setLong(2, service.getIdPet());
            pstmt.setLong(3, service.getIdTypeService());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return rs.getLong("id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public long findTransactionByService(long id) {
        String query = "SELECT idtransaction FROM services WHERE id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return rs.getLong("idtransaction");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateStateService(long id, State state) {
        String query = "UPDATE services SET state = ? WHERE id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, state.name());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
