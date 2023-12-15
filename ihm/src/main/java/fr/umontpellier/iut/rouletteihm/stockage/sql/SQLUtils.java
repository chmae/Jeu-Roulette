package fr.umontpellier.iut.rouletteihm.stockage.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    private static SQLUtils instance = null;
    private Connection connection;

    private SQLUtils() {
        String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String user = "asiamarc";
        String pass = "jorklanJ44*";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static SQLUtils getInstance() {
        if (instance == null) {
            instance = new SQLUtils();
        }
        return instance;
    }
}
