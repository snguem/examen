package Entities;

import java.util.ArrayList;
public class Annee {
private int id; 
private int Libelle;
private int Etat;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public int getLibelle() {
    return Libelle;
}
public void setLibelle(int libelle) {
    Libelle = libelle;
}
public int getEtat() {
    return Etat;
}
public void setEtat(int etat) {
    Etat = etat;
}
private ArrayList<Enregistrement> Enregistrement=new ArrayList<>() ;
public ArrayList<Enregistrement> getEnregistrement() {
    return Enregistrement;
}
public void addEnregistrement(Enregistrement enregistrement) {
    Enregistrement .add(enregistrement);
}
    
}
