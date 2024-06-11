package Entities;

import java.util.ArrayList;

public class Classe {
private int id;
private String Libelle;
private String filiere;
private String niveau;
private ArrayList<Enregistrement> Enregistrement=new ArrayList<>() ;
private ArrayList<Profclasse>  profclasse=new ArrayList<>() ;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getLibelle() {
    return Libelle;
}
public void setLibelle(String libelle) {
    Libelle = libelle;
}
public String getFiliere() {
    return filiere;
}
public void setFiliere(String filiere) {
    this.filiere = filiere;
}
public String getNiveau() {
    return niveau;
}
public void setNiveau(String niveau) {
    this.niveau = niveau;
}
public ArrayList<Enregistrement> getEnregistrement() {
    return Enregistrement;
}
public void addEnregistrement(Enregistrement enregistrement) {
    Enregistrement .add(enregistrement);
}
public ArrayList<Profclasse> getProfclasse() {
    return profclasse;
}
public void addProfclasse(Profclasse profclasse) {
    this.profclasse .add(profclasse);
}

    
}
