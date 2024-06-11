package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entities.Classe;

public class Classerepo extends Repomere{
protected final String SQL_FIND_ALL = "select * from classe";
    protected final String SQL_FIND_BY_ID = "select * from classe where id=?";
    protected final String SQL_INSERT = "INSERT INTO classe (libelle,filiere,niveau) VALUES (?,?,?)";


    public int ajouter(Classe classe) {
        int id=-1;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, classe.getLibelle());
            statement.setString(2, classe.getFiliere());
            statement.setString(3, classe.getNiveau());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
        return id;
    }
    
    public Classe rechercherClasse(int id) {
        Classe classe = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                classe = new Classe();
                
                classe.setId(rs.getInt("id"));
                classe.setLibelle(rs.getString("libelle"));
                classe.setFiliere(rs.getString("filere"));
                classe.setNiveau(rs.getString("niveau"));
                
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return classe;
    }
    
    public ArrayList<Classe> toutes() {
        ArrayList<Classe> classes = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Classe classe = new Classe();
                
                classe.setId(rs.getInt("id"));
                classe.setLibelle(rs.getString("libelle"));
                classe.setFiliere(rs.getString("filere"));
                classe.setNiveau(rs.getString("niveau"));
                
                classes.add(classe);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return classes;
    }
    
}
