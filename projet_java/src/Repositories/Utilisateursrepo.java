package Repositories;

import java.sql.ResultSet;

import Entities.Utilisateurs;

public class Utilisateursrepo extends Repomere {
private final String SQL_CONNEXION = "select * from utilisateur where login=? and password=?";
public Utilisateurs connexion (String login,String password){
Utilisateurs user = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_CONNEXION);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                user = new Utilisateurs();
                
                user.setId(rs.getInt("id"));
                user.setNomComplet(rs.getString("nomComplet"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return user;
}

}
