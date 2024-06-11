package Services;

import Entities.Utilisateurs;
import Repositories.Utilisateursrepo;

public class Utilisateursservices {
    private Utilisateursrepo utilisateursrepo=new Utilisateursrepo();


    public Utilisateurs connexion (String login,String password){
    
        return utilisateursrepo.connexion(login, password);
    
}
}