package dataaccesobject;

import database.Database;
import classe.Adherent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class adherentdao {

    // Ajouter un adhérent
    public void ajouterAdherent(Adherent adherent) throws SQLException {
        String sql = "INSERT INTO adherents (nom, prenom, adresse, type) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, adherent.getNom());
            stmt.setString(2, adherent.getPrenom());
            stmt.setString(3, adherent.getAdresse());
            stmt.setString(4, adherent.getType());
            stmt.executeUpdate();
        }
    }

    public void afficherAdherents() throws SQLException {
    String sql = "SELECT * FROM adherents";
    try (Connection conn = Database.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + 
                               ", Nom: " + rs.getString("nom") + 
                               ", Prénom: " + rs.getString("prenom") +
                               ", Type: " + rs.getString("type"));
        }
    }
}

    // Obtenir tous les adhérents
    public List<Adherent> obtenirTousLesAdherents() throws SQLException {
        List<Adherent> adherents = new ArrayList<>();
        String sql = "SELECT * FROM adherents";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Adherent adherent = new Adherent(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("adresse"),
                    rs.getString("type")
                );
                adherents.add(adherent);
            }
        }
        return adherents;
    }

    // Supprimer un adhérent
    public void supprimerAdherent(int id) throws SQLException {
        String sql = "DELETE FROM adherents WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
