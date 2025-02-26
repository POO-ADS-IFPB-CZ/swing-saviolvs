package dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public Connection getConnection() throws IOException,
            ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        prop.load(new FileReader("database.properties"));
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(prop.getProperty("url"));
    }
}