package classe;

import java.util.Date;

public class testclasse {
    public static void main(String[] args) {
        Livre livre1 = new Livre(101, "Introduction à Java", "Scientifique", "Razak", "TechBooks", new Date(), 3);

        System.out.println("Livre créé : " + livre1.getTitre() + " par " + livre1.getAuteur());
    }
}
