package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entities.Prof;


public class Profrepo extends Utilisateursrepo  {
private final String SQL_INSERT = "INSERT INTO user (nomComplet, grade, nci, role) VALUES (?, ?, ?, ?)";
private final String SQL_FIND_BY_ID = "select * from utilisateur where id=? and role=3";
private final String SQL_ALL = "select * from utilisateur where role=3";

public ArrayList<Prof> tout() {
    ArrayList<Prof> users = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_ALL);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                Prof user = new Prof();
                
                user.setId(rs.getInt("id"));
                user.setNomComplet(rs.getString("nomComplet"));
                user.setGrade(rs.getString("grade"));
                user.setNCI(rs.getInt("nci"));
                user.setRole(rs.getInt("role"));
                users.add(user);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return users;
    }
public Prof rechercheProf(int id) {
        Prof user = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                user = new Prof();
                
                user.setId(rs.getInt("id"));
                user.setNomComplet(rs.getString("nomComplet"));
                user.setGrade(rs.getString("grade"));
                user.setNCI(rs.getInt("nci"));
                user.setRole(rs.getInt("role"));
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return user;
    }
    public int ajouter(Prof user) {
        int id=-1;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, user.getNomComplet());
            statement.setString(2, user.getGrade());
            statement.setInt(3, user.getNCI());
            statement.setInt(4, user.getRole());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
        return id;
    }
    
}
