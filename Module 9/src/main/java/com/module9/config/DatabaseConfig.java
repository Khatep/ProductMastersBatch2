package com.module9.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseConfig {
    static String URL = "jdbc:postgresql://localhost:5432/module9";
    static String USER = "postgres";
    static String PASSWORD = "123";

    public static Connection getConnectionToDatabase() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            log.error("Failed to connect database: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
