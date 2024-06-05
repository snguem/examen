package services;

import entities.Prof;
import repositories.Profrepo;

public class Profservices {
    private Profrepo profrepo=new Profrepo();
      public Prof chercher(int id) {
        
        return profrepo.chercher(id);
    }
    
    public int ajouter(Prof user) {
        
        return profrepo.ajouter(user);
    }
}
