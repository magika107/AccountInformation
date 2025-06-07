package mft.model.repository;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    @Getter
    private static ConnectionProvider connectionProvider = new ConnectionProvider();
    private static BasicDataSource basicDataSource = new BasicDataSource();


    private ConnectionProvider() {
    }

    public Connection getConnection() throws SQLException {
        // basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // basicDataSource.setUrl("jdbc:mysql://localhost:3306/meta_laptop");

        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("java");
        basicDataSource.setPassword("java123");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxTotal(20);
        return basicDataSource.getConnection();


//        Connection connection = DriverManager.getConnection(
//                "jdbc:oracle:thin:@localhost:1521:xe",
//                "java",
//                "java123"
//        );
        //  connection.setAutoCommit(false);
        //return connection;
    }
}

