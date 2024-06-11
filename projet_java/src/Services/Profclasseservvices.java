package Services;

import java.util.ArrayList;

import Entities.Prof;
import Entities.Profclasse;
import Repositories.Profclasserepo;

public class Profclasseservvices {
    private Profclasserepo profclasserepo=new Profclasserepo();


      public void ajouter(Profclasse classeProf) {
       profclasserepo.ajouter(classeProf);
    }
    

    public ArrayList<Profclasse> toutParProf(Prof prof) {
       
        return profclasserepo.toutParProf(prof) ;
}
}