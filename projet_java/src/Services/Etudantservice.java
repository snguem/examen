package Services;


import Entities.Etudiant;
import Repositories.Etudiantrepo;

public class Etudantservice {
    private Etudiantrepo etudiantrepo=new Etudiantrepo();


    public Etudiant rechercheEtudiant(int id) {
    
        return etudiantrepo.rechercheEtudiant(id);
    }
    public int ajouter(Etudiant user) {
        
        return etudiantrepo.ajouter(user);
    }
}
