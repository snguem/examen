package repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import entities.Cours;
import entities.Prof;
import entities.Module;

public class Coursrepo extends Repomere{
    private Profrepo profrepo=new Profrepo();
    private Modulerepo modulerepo=new Modulerepo();
    protected final String SQL_FIND_ALL = "select * from cours";
    protected final String SQL_FIND_BY_ID = "select * from cours where libelle=?";
    protected final String SQL_INSERT = "INSERT INTO cours (date,heureDb,heureFin,prof,module) VALUES (?,?,?,?,?)";


    public int ajouter(Cours cours) {
            int id=-1;
            try {
                ouvrirConnexion();
                initPrepareStatement(SQL_INSERT);
                statement.setDate(1, Date.valueOf(cours.getDate()));
                statement.setTime(2, Time.valueOf(cours.getHeureD()));
                statement.setTime(3, Time.valueOf(cours.getHeureF()));
                statement.setInt(4, cours.getProf().getId());
                statement.setInt(5, cours.getModule().getId());
        
                id=executeUpdate();
                fermerConnexion();
            } catch (Exception e) {
                System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
            }
            return id;
        }
        
    public Cours rechercherCours(int id) {
        Cours cours = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                cours = new Cours();
                Prof p=new Prof();
                p = profrepo.chercher(rs.getInt("prof"));
                Module m=new Module();
                int id_=rs.getInt("module");
                m = modulerepo.toutes().stream().filter(module->module.getId()==id_).findFirst().orElse(null);
                
                cours.setId(rs.getInt("id"));
                cours.setDate(rs.getDate("date").toLocalDate());
                cours.setHeureD(rs.getTime("heureDb").toLocalTime());
                cours.setHeureF(rs.getTime("heureFin").toLocalTime());
                cours.setProf(p);
                cours.setModule(m);
                
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return cours;
    }
    
    public ArrayList<Cours> toutes() {
        ArrayList<Cours> cours = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Cours cour = new Cours();
                Prof p=new Prof();
                p = profrepo.chercher(rs.getInt("prof"));
                Module m=new Module();
                int id_=rs.getInt("module");
                m = modulerepo.toutes().stream().filter(module->module.getId()==id_).findFirst().orElse(null);
                
                cour.setId(rs.getInt("id"));
                cour.setDate(rs.getDate("date").toLocalDate());
                cour.setHeureD(rs.getTime("heureDb").toLocalTime());
                cour.setHeureF(rs.getTime("heureFin").toLocalTime());
                cour.setProf(p);
                cour.setModule(m);
                
                cours.add(cour);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return cours;

}
}
