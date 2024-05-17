package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbtest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";
    Logger logger = Logger.getLogger(Util.class.getName());

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            logger.log(Level.INFO, "Connection successfully");
        } catch (SQLException e) {
            logger.log(Level.INFO, "Failed to connect to database");
        }
        return connection;
    }
}
