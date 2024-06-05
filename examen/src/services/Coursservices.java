package services;

import java.util.ArrayList;

import entities.Cours;
import repositories.Coursrepo;

public class Coursservices {
    private Coursrepo coursrepo=new Coursrepo();
    
    public int ajouter(Cours cours) {
            return coursrepo.ajouter(cours) ;
        }
        
    public Cours rechercherCours(int id) {
    
        return coursrepo.rechercherCours(id);
    }
    
    public ArrayList<Cours> toutes() {
       
        return coursrepo.toutes();

}
}
