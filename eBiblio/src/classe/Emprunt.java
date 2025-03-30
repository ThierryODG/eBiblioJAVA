package classe;

import java.util.Date;

public class Emprunt {
    private int id;
    private int idAdherent;
    private int idLivre;
    private Date dateDebut;
    private Date dateRetour;
    private String statut;

    public Emprunt(int id, int idAdherent, int idLivre, Date dateDebut, Date dateRetour, String statut) {
        this.id = id;
        this.idAdherent = idAdherent;
        this.idLivre = idLivre;
        this.dateDebut = dateDebut;
        this.dateRetour = dateRetour;
        this.statut = statut;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAdherent() { return idAdherent; }
    public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }

    public int getIdLivre() { return idLivre; }
    public void setIdLivre(int idLivre) { this.idLivre = idLivre; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date dateRetour) { this.dateRetour = dateRetour; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}



