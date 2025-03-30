package ebiblio;

import dataaccesobject.livredao;
import classe.Livre;
import java.sql.SQLException;
import java.util.Date;

public class EBiblio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testerAjoutLivre();
        testerAffichageLivres();
        testerSuppressionLivre();
    }

    // Tester l'ajout d'un livre
    private static void testerAjoutLivre() {
        try {
            // Créer un livre
            livredao livredao = new livredao();
            Livre livre = new Livre(0, "Java Programming", "Informatique", "James Gosling", "Sun Microsystems", new Date(), 5);
            livredao.ajouterLivre(livre);  // Ajouter le livre
            System.out.println("Livre ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();  // Affiche l'erreur en cas de problème
        }
    }

    // Tester l'affichage de tous les livres
    private static void testerAffichageLivres() {
        try {
            livredao livredao = new livredao();
            livredao.afficherLivres();  // Afficher tous les livres
        } catch (SQLException e) {
            e.printStackTrace();  // Affiche l'erreur en cas de problème
        }
    }

    // Tester la suppression d'un livre
    private static void testerSuppressionLivre() {
        try {
            livredao livredao = new livredao();
            // Supposons qu'on veut supprimer le livre avec l'ID 1
            livredao.supprimerLivre(1);  // Supprimer un livre par son ID
            System.out.println("Livre supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();  // Affiche l'erreur en cas de problème
        }
    }
}
