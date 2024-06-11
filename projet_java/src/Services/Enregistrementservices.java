package Services;

import java.util.ArrayList;

import Entities.Annee;
import Entities.Enregistrement;
import Entities.Etudiant;
import Repositories.Enregistrementrepo;

public class Enregistrementservices {
    private Enregistrementrepo enregistrementrepo=new Enregistrementrepo();


     public int ajouter(Enregistrement enregistrement) {
      
        return enregistrementrepo.ajouter(enregistrement);
    }
    
    public Enregistrement chercherParEtudiantAnnee(Etudiant etudiant, Annee annee) {
       
        return enregistrementrepo.chercherParEtudiantAnnee(etudiant, annee);
    }
    
    public ArrayList<Enregistrement>  rechercherParAnnee(Annee annee) {
       
    
        return enregistrementrepo.rechercherParAnnee(annee);
    }
    
}
