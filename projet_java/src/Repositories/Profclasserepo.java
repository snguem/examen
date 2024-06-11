package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entities.Prof;
import Entities.Profclasse;

public class Profclasserepo extends Repomere {
    private  final String SQL_SELECT_BY_PROF = "select cl.* from profclasse cl, classe c, utilisateur prof where cl.classe=c.id and cl.prof=prof.id and cl.prof=?";
    private  final String SQL_INSERT = "INSERT INTO profclasse (classe,prof) VALUES (?,?)";
   private Classerepo classerepo=new Classerepo();
   private Profrepo profrepo=new Profrepo();
    
    
    public void ajouter(Profclasse classeProf) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setInt(1, classeProf.getClasse().getId());
            statement.setInt(2, classeProf.getProf().getId());
            executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
    }
    

    public ArrayList<Profclasse> toutParProf(Prof prof) {
        ArrayList<Profclasse>  profclasse= new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_BY_PROF);
            statement.setInt(1, prof.getId());
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Profclasse classeProf = new Profclasse();
                
                classeProf.setId(rs.getInt("id"));
                classeProf.setClasse(classerepo.rechercherClasse(rs.getInt("classe")));
                classeProf.setProf(profrepo.rechercheProf(rs.getInt("prof")));
                
                profclasse.add(classeProf);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("ici");
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return profclasse;
    }
}
