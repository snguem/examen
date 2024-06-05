package repositories;

import java.sql.ResultSet;

import entities.Prof;



public class Profrepo extends Repomere {
    private final String SQL_INSERT = "INSERT INTO prof (nom, prenom,tel) VALUES (?, ?, ?)";
private final String SQL_FIND_BY_ID = "select * from prof where id=?";
    public Prof chercher(int id) {
        Prof user = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                user = new Prof();
                
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setTel(rs.getString("tel"));
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
            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getTel());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
        return id;
    }
    
}
