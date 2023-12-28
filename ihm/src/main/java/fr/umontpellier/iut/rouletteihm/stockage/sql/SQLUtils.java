package fr.umontpellier.iut.rouletteihm.stockage.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour la connexion à la base de données
 */
public class SQLUtils {
    private static SQLUtils instance = null;
    private Connection connection;

    // Constructeur privé pour empêcher l'instanciation directe de la classe depuis l'extérieur
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

    // Méthode pour récupérer la connexion
    public Connection getConnection() {
        return this.connection;
    }

    // Méthode pour récupérer l'instance de la classe
    public static SQLUtils getInstance() {
        if (instance == null) {
            instance = new SQLUtils();
        }
        return instance;
    }
}
