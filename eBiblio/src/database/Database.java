package database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque"; 
    private static final String USER = "root"; // 
    private static final String PASSWORD = ""; // 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trouvé !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données !");
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println("Test de connexion...");
        Connection conn = Database.getConnection();
        if (conn != null) {
            System.out.println("Connexion établie !");
        } else {
            System.out.println("Échec de connexion !");
        }
    }
}
