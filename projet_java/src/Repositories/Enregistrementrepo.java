package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entities.Annee;
import Entities.Enregistrement;
import Entities.Etudiant;

public class Enregistrementrepo extends Repomere {
    protected final String SQL_INSERT = "INSERT INTO enregistrement (date,montant, type, annee,etudiant,classe) VALUES(?, ?, ?, ?, ?, ?)";
    protected final String SQL_FIND_BY_ETUDIANT_ANNEE = "SELECT * FROM enregistrement where etudiant=? and annee=?";
    protected final String SQL_FIND_BY_ANNEE = "SELECT * FROM enregistrement where annee=? group by etudiant";
    private Classerepo classerepo=new Classerepo();
    private Etudiantrepo etudiantrepo=new Etudiantrepo();
    
    public int ajouter(Enregistrement enregistrement) {
        int id=-1;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setDate(1, enregistrement.getDate());
            statement.setDouble(2, enregistrement.getMontant());
            statement.setInt(3, enregistrement.getType());
            statement.setInt(4, enregistrement.getAnnee().getId());
            statement.setInt(5, enregistrement.getEtudiant().getId());
            statement.setInt(6, enregistrement.getClasse().getId());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
        return id;
    }
    
    public Enregistrement chercherParEtudiantAnnee(Etudiant etudiant, Annee annee) {
        Enregistrement enregistrement = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ETUDIANT_ANNEE);
            statement.setInt(1, etudiant.getId());
            statement.setInt(2, annee.getId());
            ResultSet rs = executeSelect();
            if (rs.next()) {
                enregistrement = new Enregistrement();

                enregistrement.setId(rs.getInt("id"));
                enregistrement.setDate(rs.getDate("date"));
                enregistrement.setMontant(rs.getDouble("montant"));
                enregistrement.setType(rs.getInt("type"));
                enregistrement.setAnnee(annee);
                enregistrement.setEtudiant(etudiant);
                enregistrement.setClasse(classerepo.rechercherClasse(rs.getInt("classe")));
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return enregistrement;
    }
    
    public ArrayList<Enregistrement>  rechercherParAnnee(Annee annee) {
        ArrayList<Enregistrement>  enregistrements = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ANNEE);
            statement.setInt(1, annee.getId());
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Enregistrement enregistrement = new Enregistrement();

                enregistrement.setId(rs.getInt("id"));
                enregistrement.setDate(rs.getDate("date"));
                enregistrement.setMontant(rs.getDouble("montant"));
                enregistrement.setType(rs.getInt("type"));
                enregistrement.setAnnee(annee);
                enregistrement.setEtudiant(etudiantrepo.rechercheEtudiant(rs.getInt("etudiant")));
                enregistrement.setClasse(classerepo.rechercherClasse(rs.getInt("classe")));
                
                enregistrements.add(enregistrement);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return enregistrements;
    }
    
}
