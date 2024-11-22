package com.microservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Database {


    private String url;
    private String username;
    private String password;
    private String driver;
    private static Connection instance;

    public Database(@Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password, @Value("${spring.datasource.driver-class-name}") String driver) throws ClassNotFoundException {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
        Class.forName(driver);
    }

    public static synchronized Connection getInstance(Database db) throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(db.url, db.username, db.password);
            System.out.println("Conexión exitosa a PostgreSQL");
        }
        return instance;
    }
    public void close() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
