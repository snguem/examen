package Services;


import java.util.ArrayList;

import Entities.Prof;
import Repositories.Profrepo;

public class Profservices {
    private Profrepo profrepo= new Profrepo();
    

    public ArrayList<Prof> tout() {
    
        return profrepo.tout();
    }
    public Prof rechercheProf(int id) {
    
        return profrepo.rechercheProf(id);
    }
    public int ajouter(Prof user) {
       
        return profrepo.ajouter(user);
    }
}
