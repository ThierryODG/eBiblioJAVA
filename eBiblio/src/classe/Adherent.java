package classe;

public class Adherent {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String type; // Étudiant, Enseignant ou Visiteur

    // Constructeur
    public Adherent(int id, String nom, String prenom, String adresse, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.type = type;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
