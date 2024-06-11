package Entities;

import java.util.ArrayList;

public class Etudiant extends Utilisateurs {
    private int matricule;
    private String tuteur ;
    public String getTuteur() {
        return tuteur;
    }
    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }
    private ArrayList <Enregistrement> enregistrements=new ArrayList<>() ;
    public int getMatricule() {
        return matricule;
    }
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
    public ArrayList<Enregistrement> getEnregistrements() {
        return enregistrements;
    }
    public void addEnregistrement(Enregistrement enregistrement) {
        this.enregistrements .add(enregistrement);
    }


}
