package Repositories;

import java.sql.ResultSet;

import Entities.Annee;

public class Anneerepo extends Repomere {
    private final String SQL_FIND_BY_LIBELLE = "select * from annee where libelle=?";
    private final String SQL_ANNEE_PASSEE = "update annee set etat=0 where id=?";
    private final String SQL_INSERT = "INSERT INTO annee (libelle,etat) VALUES (?,?)";
    private final String SQL_FIND_ACTUEL = "select * from annee where etat=1";
    public void ajoutannee (Annee annee){
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setInt(1, annee.getLibelle());
            statement.setInt(2, annee.getEtat());
            executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
    }
    public void anneepassee(Annee annee){
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_ANNEE_PASSEE);
            statement.setInt(1, annee.getId());

            executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        }
    public Annee rechercheannee(int libelle){
        Annee annee = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_LIBELLE);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                annee = new Annee();
                
                annee.setId(rs.getInt("id"));
                annee.setLibelle(rs.getInt("libelle"));
                annee.setEtat(rs.getInt("etat"));
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return annee;
    }
    public Annee actuel() {
        Annee annee = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ACTUEL);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                annee = new Annee();
                
                annee.setId(rs.getInt("id"));
                annee.setLibelle(rs.getInt("libelle"));
                annee.setEtat(rs.getInt("etat"));
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return annee;
    }
}

