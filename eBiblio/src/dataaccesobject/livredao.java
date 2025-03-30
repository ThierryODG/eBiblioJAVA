package dataaccesobject;

import database.Database;
import classe.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class livredao {

    // Ajouter un livre
    public void ajouterLivre(Livre livre) throws SQLException {
        String sql = "INSERT INTO livres (titre, type, auteur, editeur, date_edition, nombre_exemplaires) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getType());
            stmt.setString(3, livre.getAuteur());
            stmt.setString(4, livre.getEditeur());
            stmt.setDate(5, new java.sql.Date(livre.getDateEdition().getTime())); // ✅ Assure-toi que getDateEdition() retourne un Date
            stmt.setInt(6, livre.getNombreExemplaires());
            stmt.executeUpdate();
        }
    }

    //  Obtenir tous les livres
    public List<Livre> obtenirTousLesLivres() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livres";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livre livre = new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("type"),
                    rs.getString("auteur"),
                    rs.getString("editeur"),
                    rs.getDate("dateEdition"), // ✅ Correspond bien au constructeur corrigé
                    rs.getInt("nombreExemplaires")
                );
                livres.add(livre);
            }
        }
        return livres;
    }
    
    public void afficherLivres() throws SQLException {
    String sql = "SELECT * FROM livres";
    try (Connection conn = Database.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + 
                               ", Titre: " + rs.getString("titre") + 
                               ", Auteur: " + rs.getString("auteur") +
                               ", Nombre d'exemplaires: " + rs.getInt("nombreExemplaires"));
        }
    }
}


    //  Supprimer un livre
    public void supprimerLivre(int id) throws SQLException {
        String sql = "DELETE FROM livres WHERE code = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
