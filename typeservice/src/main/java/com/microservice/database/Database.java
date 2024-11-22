package com.microservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class Database {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private static Connection instance;

    private Database() {
    }

    public static synchronized Connection getInstance(Database db) throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(db.url, db.username, db.password);
            System.out.println("Conexión exitosa a PostgreSQL");
        }
        return instance;
    }

  /*   // Ejecutar una consulta SELECT
    public ResultSet query(String query, Object... params) throws SQLException {
        PreparedStatement statement = getInstance(this).prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement.executeQuery();
    }

    // Ejecutar una consulta INSERT, UPDATE, DELETE
    public long execute(String query, Object... params) throws SQLException {
        PreparedStatement statement = getInstance(this).prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement.executeUpdate();
    }
*/

    public Connection getConnection() {
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
