package repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import entities.Module;



public class Modulerepo extends Repomere{
    protected final String SQL_FIND_ALL = "select * from module";
    protected final String SQL_FIND_BY_ID = "select * from module where libelle=?";
    protected final String SQL_INSERT = "INSERT INTO module (libelle) VALUES (?)";


    public int ajouter(Module module) {
            int id=-1;
            try {
                ouvrirConnexion();
                initPrepareStatement(SQL_INSERT);
                statement.setString(1, module.getLibelle());
        
                id=executeUpdate();
                fermerConnexion();
            } catch (Exception e) {
                System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
            }
            return id;
        }
        
    public Module rechercherModule(String libelle) {
        Module module = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setString(1, libelle);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                module = new Module();
                
                module.setId(rs.getInt("id"));
                module.setLibelle(rs.getString("libelle"));
                
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return module;
    }
    
    public ArrayList<Module> toutes() {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Module module = new Module();
                
                module.setId(rs.getInt("id"));
                module.setLibelle(rs.getString("libelle"));
                
                modules.add(module);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return modules;

}
}
