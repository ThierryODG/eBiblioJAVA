package dataaccesobject;

import dataaccesobject.livredao;
import dataaccesobject.adherentdao;
import dataaccesobject.empruntdao;
import classe.Livre;
import classe.Adherent;
import classe.Emprunt;
import java.sql.SQLException;
import java.util.Date;

public class testdao {
    public static void main(String[] args) {
        try {
            // Tester l'ajout d'un livre
            livredao livredao = new livredao();
            Livre livre = new Livre(0, "Java Programming", "Informatique", "James Gosling", "Sun Microsystems", new Date(), 5);
            livredao.ajouterLivre(livre);
            System.out.println("Livre ajouté avec succès.");
            
            // Afficher tous les livres
            livredao.afficherLivres();
            
            // Tester l'ajout d'un adhérent
            adherentdao adherentdao = new adherentdao();
            Adherent adherent = new Adherent(0, "Doe", "John", "123 Rue", "Etudiant");
            adherentdao.ajouterAdherent(adherent);
            System.out.println("Adhérent ajouté avec succès.");
            
            // Afficher tous les adhérents
            adherentdao.afficherAdherents();
            
            // Tester l'ajout d'un emprunt
            empruntdao empruntdao = new empruntdao();
            Emprunt emprunt = new Emprunt(0, 1, 1, new Date(), new Date(), "En cours");
            empruntdao.ajouterEmprunt(emprunt);
            System.out.println("Emprunt ajouté avec succès.");
            
            // Afficher tous les emprunts
            empruntdao.afficherEmprunts();
            
        } catch (SQLException e) {
            e.printStackTrace();  // Affiche les erreurs SQL
        }
    }
}
