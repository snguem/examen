package Services;

import java.util.ArrayList;

import Entities.Classe;
import Repositories.Classerepo;

public class Classeservices {
    private Classerepo classerepo=new Classerepo();

     public int ajouter(Classe classe) {
  
        return classerepo.ajouter(classe);
    }
    
    public Classe rechercherClasse(int id) {
        return classerepo.rechercherClasse(id);
    }
    
    public ArrayList<Classe> toutes() {
        
        
        return classerepo.toutes();
    }
}
