package Services;

import Entities.Annee;
import Repositories.Anneerepo;

public class Anneeservices {
private Anneerepo anneerepo=new Anneerepo();

 public void ajoutannee (Annee annee){
     anneerepo.anneepassee(annee);
    annee.setLibelle(annee.getLibelle()+1);
    anneerepo.ajoutannee(annee);
    }
    public Annee rechercheannee(int libelle){
        return anneerepo.rechercheannee(libelle);
    }
    public Annee actuel() {
        Annee annee=anneerepo.actuel();
        if (annee==null) {
          annee=new Annee();
           annee.setLibelle(2022);
            annee.setEtat(1);
            anneerepo.ajoutannee(annee);
            annee=anneerepo.actuel();
        }
        return annee;
    }
}
