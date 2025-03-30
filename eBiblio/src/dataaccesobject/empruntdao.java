package dataaccesobject;

import database.Database;
import classe.Emprunt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class empruntdao {

    // Ajouter un emprunt
    public void ajouterEmprunt(Emprunt emprunt) throws SQLException {
        String sql = "INSERT INTO emprunts (idAdherent, idLivre, dateDebut, dateRetour, statut) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprunt.getIdAdherent());
            stmt.setInt(2, emprunt.getIdLivre());
            stmt.setDate(3, new java.sql.Date(emprunt.getDateDebut().getTime()));
            stmt.setDate(4, new java.sql.Date(emprunt.getDateRetour().getTime()));
            stmt.setString(5, emprunt.getStatut());
            stmt.executeUpdate();
        }
    }

    public void afficherEmprunts() throws SQLException {
    String sql = "SELECT * FROM emprunts";
    try (Connection conn = Database.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + 
                               ", Livre ID: " + rs.getInt("livre_id") + 
                               ", Adhérent ID: " + rs.getInt("adherent_id") +
                               ", Date Emprunt: " + rs.getDate("dateEmprunt") +
                               ", Statut: " + rs.getString("statut"));
        }
    }
}

    // Obtenir tous les emprunts
    public List<Emprunt> obtenirTousLesEmprunts() throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM emprunts";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                    rs.getInt("id"),
                    rs.getInt("idAdherent"),
                    rs.getInt("idLivre"),
                    rs.getDate("dateDebut"),
                    rs.getDate("dateRetour"),
                    rs.getString("statut")
                );
                emprunts.add(emprunt);
            }
        }
        return emprunts;
    }

    // Supprimer un emprunt
    public void supprimerEmprunt(int id) throws SQLException {
        String sql = "DELETE FROM emprunts WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
