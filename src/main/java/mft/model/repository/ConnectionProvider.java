package mft.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static ConnectionProvider connectionProvider=new ConnectionProvider();
    private ConnectionProvider() {

    }
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                "root",
                "java123");
    }
}
