package classe;

import java.util.Date;

public class Livre {
    private int id;
    private String titre;
    private String type;
    private String auteur;
    private String editeur;
    private Date dateEdition;
    private int nombreExemplaires;

    // Constructeur avec Date
    public Livre(int id, String titre, String type, String auteur, String editeur, Date dateEdition, int nombreExemplaires) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.auteur = auteur;
        this.editeur = editeur;
        this.dateEdition = dateEdition;
        this.nombreExemplaires = nombreExemplaires;
    }

    Livre(int i, String introduction_à_Java, String martin, String techBooks, String scientifique, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // ✅ Getters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getType() { return type; }
    public String getAuteur() { return auteur; }
    public String getEditeur() { return editeur; }
    public Date getDateEdition() { return dateEdition; }  // ✅ Retourne bien un Date
    public int getNombreExemplaires() { return nombreExemplaires; }

    // ✅ Setters
    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setType(String type) { this.type = type; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public void setEditeur(String editeur) { this.editeur = editeur; }
    public void setDateEdition(Date dateEdition) { this.dateEdition = dateEdition; }
    public void setNombreExemplaires(int nombreExemplaires) { this.nombreExemplaires = nombreExemplaires; }
}
