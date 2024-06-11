package Repositories;

import java.sql.ResultSet;

import Entities.Etudiant;

public class Etudiantrepo extends Utilisateursrepo {
private final String SQL_INSERT = "INSERT INTO user (nomComplet, tuteur, matricule, role) VALUES (?, ?, ?, ?)";
private final String SQL_FIND_BY_ID = "select * from utilisateur where id=? and role=4";
public Etudiant rechercheEtudiant(int id) {
        Etudiant user = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                user = new Etudiant();
                
                user.setId(rs.getInt("id"));
                user.setNomComplet(rs.getString("nomComplet"));
                user.setTuteur(rs.getString("tuteur"));
                user.setMatricule(rs.getInt("matricule"));
                user.setRole(rs.getInt("role"));
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return user;
    }
    public int ajouter(Etudiant user) {
        int id=-1;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, user.getNomComplet());
            statement.setString(2, user.getTuteur());
            statement.setInt(3, user.getMatricule());
            statement.setInt(4, user.getRole());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
        return id;
    }
    
}
