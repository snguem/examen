package services;

import java.util.ArrayList;

import repositories.Modulerepo;
import entities.Module;

public class Moduleservices {
    private Modulerepo modulerepo=new Modulerepo();
    public int ajouter(Module module) {
            
            return modulerepo.ajouter(module);
        }
        
    public Module rechercherModule(String libelle) {
        
        return modulerepo.rechercherModule(libelle);
    }
    
    public ArrayList<Module> toutes() {
       
        return modulerepo.toutes();

}
}
