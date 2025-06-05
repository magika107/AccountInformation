import mft.model.repository.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class TheTestconnect {


    public static void main(String[] args) {
        try {
            Connection connection = ConnectionProvider.getConnectionProvider().getConnection();
            System.out.println(" اتصال به دیتابیس با موفقیت برقرار شد.");
            connection.close();
        } catch (SQLException e) {
            System.out.println(" خطا در اتصال به دیتابیس:");
            e.printStackTrace();
        }
    }
}


