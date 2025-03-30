package classe ;

public class testclasse {
    public static void main(String[] args) {
        // Création d'un adhérent
        Adherent adherent1 = new Adherent(1, "Sanfo", "Razak", "Ouagadougou", "Étudiant");
        System.out.println("Adhérent créé : " + adherent1.getNom() + " " + adherent1.getPrenom());

        // Création d'un livre
        Livre livre1 = new Livre(101, "Introduction à Java", "Martin", "TechBooks", "Scientifique", 3);
        System.out.println("Livre créé : " + livre1.getTitre() + " par " + livre1.getAuteur());
    }
}
